package com.yqw.my.tomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iQiwen on 2019/6/26.
 */
public class MyTomcat {
    private int port;
    private Map<String, String> urlServletMap = new HashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    /**
     * Tomcat的处理流程：把URL对应处理的Servlet关系形成，解析HTTP协议，
     * 封装请求/响应对象，利用反射实例化具体的Servlet进行处理即可。
     */
    public void start() {
        initServletMapping();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("My Tomcat is starting...");

            while (true) {
                Socket socket = serverSocket.accept();

                MyRequest request = new MyRequest(socket.getInputStream());
                MyResponse response = new MyResponse(socket.getOutputStream());
                dispatch(request, response);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            System.out.println("initServletMapping url=" + servletMapping.getUrl() + " ,clazz=" + servletMapping.getClazz());
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    private void dispatch(MyRequest request, MyResponse response) {
        String clazz = urlServletMap.get(request.getUrl());

        if (clazz == null) {
            try {
                response.write("404 Not Found!");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //反射
        try {
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = myServletClass.newInstance();
            myServlet.service(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }
}
