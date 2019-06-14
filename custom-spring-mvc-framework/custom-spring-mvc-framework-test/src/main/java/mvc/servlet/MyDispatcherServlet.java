package mvc.servlet;

import mvc.annotation.MyAutowired;
import mvc.annotation.MyController;
import mvc.annotation.MyRequestMapping;
import mvc.annotation.MyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * Created by Qiwen on 2019/6/1.
 */
public class MyDispatcherServlet extends HttpServlet {

    //和web.xml中param-name中的值一致
    private static final String LOCATION = "contextConfigLocation";
    //保存所有的配置信息
    private Properties properties = new Properties();
    //保存所有被扫描到的相关的类名
    private List<String> classNames = new ArrayList<String>();
    //核心IOC容器，保存所有初始化的Bean
    private Map<String, Object> iocMap = new HashMap<String, Object>();
    //保存所有的url和方法的映射关系
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //1、加载配置文件
        doLoadConfig(servletConfig.getInitParameter(LOCATION));

        //2、扫描所有相关的类
        doScanner(properties.getProperty("scanPackage"));

        //3、初始化刚刚扫描到的相关的类，并把它保存在IOC容器中
        doInstance();

        //4、实现依赖注入，DI,自动赋值
        doAutowired();


        //5、初始化HandlerMapping
        initHandlerMapping();

        System.out.println("My Spring MVC is init");

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //运行阶段，根据用户请求的url进行分发和调度
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception ,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
        }
    }


    private void initHandlerMapping() {
        if (iocMap.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : iocMap.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }
            String baseUrl = "";
            //获取controller的url配置
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            //获取method的url配置
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }

                //映射URL
                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("/+", "/"));
                handlerMapping.put(url, method);
                System.out.println("mapped" + url + " , " + method);
            }
        }

    }

    private void doInstance() {
        if (classNames.size() == 0) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    //默认首字母小写的beanName
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    iocMap.put(beanName, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    MyService service = clazz.getAnnotation(MyService.class);
                    String beanName = service.value();
                    //如果用户设置了名字，就用用户自己设置的
                    if (!"".equals(beanName.trim())) {
                        iocMap.put(beanName, clazz.newInstance());

                    } else {
                        //如果自己没设置，则按接口类型创建一个实例
                        Class<?>[] interfaces = clazz.getInterfaces();
                        for (Class<?> i : interfaces) {
                            iocMap.put(i.getName(), clazz.newInstance());
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doAutowired() {
        if (iocMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : iocMap.entrySet()) {
            //拿到实例对象中的所有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }
                MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                String beanName = myAutowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);//设置私有属性的访问权限
                try {
                    field.set(entry.getValue(), iocMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private void doScanner(String packageName) {
        //将所有的包路径转换为文件路径
        URL url = getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }

    }

    private void doLoadConfig(String location) {
        InputStream fis = getClass().getClassLoader().getResourceAsStream(location);
        //1读取配置文件
        try {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (handlerMapping.isEmpty()) {
            return;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        if (!handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found!");
            return;
        }
        // Map<String, String[]> params = req.getParameterMap();
        Method method = handlerMapping.get(url);
        //获取方法的参数列表
        Class<?>[] parameterTypes = method.getParameterTypes();
        //获取请求的参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        //保存参数数值
        Object[] paramValues = new Object[parameterTypes.length];
        //方法的参数列表
        for (int i = 0; i < parameterTypes.length; i++) {
            //根据参数名称，做某些处理
            Class parameterType = parameterTypes[i];
            if (parameterType == HttpServletRequest.class) {
                //参数类型已经明确，这边强转类型
                paramValues[i] = req;
                // continue;
            } else if (parameterType == HttpServletResponse.class) {
                paramValues[i] = resp;
                // continue;
            } else if (parameterType == String.class) {
                for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                    String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n");
                    paramValues[i] = value;
                }
            }
        }

        try {
            String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
            //利用反射机制调用
            method.invoke(iocMap.get(beanName), paramValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 手字母小写
     */
    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
