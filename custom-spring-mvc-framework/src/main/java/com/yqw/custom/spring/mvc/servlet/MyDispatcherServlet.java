package com.yqw.custom.spring.mvc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Qiwen on 2019/6/1.
 */
public class MyDispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //1、加载配置文件
        doLoadConfig();

        //2、扫描所有相关的类
        doScanner();

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

        //运行阶段，根据用户请求的url进行分发和调度
        doDispatch(req, resp);
    }


    private void initHandlerMapping() {
    }

    private void doInstance() {


    }

    private void doAutowired() {
    }

    private void doScanner() {


    }

    private void doLoadConfig() {

    }


    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
    }


}
