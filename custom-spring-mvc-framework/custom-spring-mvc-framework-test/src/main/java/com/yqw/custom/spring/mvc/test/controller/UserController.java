package com.yqw.custom.spring.mvc.test.controller;

import com.yqw.custom.spring.mvc.annotation.MyAutowired;
import com.yqw.custom.spring.mvc.annotation.MyController;
import com.yqw.custom.spring.mvc.test.service.UserService;

/**
 * Created by Qiwen on 2019/6/1.
 */
@MyController
public class UserController {

    @MyAutowired
    private UserService userService;


}
