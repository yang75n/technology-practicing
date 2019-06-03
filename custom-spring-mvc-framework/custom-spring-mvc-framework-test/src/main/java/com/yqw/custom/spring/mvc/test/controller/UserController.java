package com.yqw.custom.spring.mvc.test.controller;


import com.yqw.custom.spring.mvc.test.service.UserService;
import mvc.annotation.MyAutowired;
import mvc.annotation.MyController;
import mvc.annotation.MyRequestMapping;
import mvc.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Qiwen on 2019/6/1.
 */
@MyController
@MyRequestMapping("/user")
public class UserController {

    @MyAutowired
    private UserService userService;

    @MyRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @MyRequestParam("name") String name) {
        String result = userService.get(name);
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @MyRequestParam("a") Integer a, @MyRequestParam("b") Integer b) {
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/remove")
    public void remove(HttpServletRequest req, HttpServletResponse resp,
                       @MyRequestParam("id") Integer id) {

    }

}
