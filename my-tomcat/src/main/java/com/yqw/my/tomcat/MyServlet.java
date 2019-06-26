package com.yqw.my.tomcat;

/**
 * Tomcat是满足Servlet规范的容器，
 * 那么自然Tomcat需要提供API。这里你看到了Servlet常见的doGet/doPost/service方法。
 */
public abstract class MyServlet {

    public abstract void doGet(MyRequest request, MyResponse response);

    public abstract void doPost(MyRequest request, MyResponse response);

    public void service(MyRequest request, MyResponse response) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            doPost(request, response);
        }
    }
}
