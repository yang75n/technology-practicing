package com.yqw.order.sso.client.interceptor;

import com.yqw.order.sso.client.util.HttpUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Qiwen on 2019/6/14.
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截了");
        //在order.com中判断是否有session对象
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && httpSession.getAttribute("isLogin").equals("logined")) {
            return true;
        }

        String token = request.getParameter("token");
        System.out.println("token=" + token);
        if (token != null) {
            String reqUrl = "http://www.sso.com:8080/checkTocken";
            String content = "toeken=" + token;
            //发送请求，验证token
            String result = HttpUtil.sendGet(reqUrl, content);
            if ("correct".equals(result)) {
                //在user.com中创建局部session会话
                request.getSession().setAttribute("isLogin", "logined");
                return true;
            }
        }

        //表示当你需要访问user.com中的某个资源时，既没有session信息，也没有其他系统已经登录过得token信息
        response.sendRedirect("http://www.sso.com：8080/preLogin?url=www.order.com:8082/order/wel");

        return false;
    }
}
