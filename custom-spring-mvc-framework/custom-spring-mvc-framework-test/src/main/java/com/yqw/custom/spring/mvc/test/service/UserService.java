package com.yqw.custom.spring.mvc.test.service;


import mvc.annotation.MyService;

/**
 * Created by Qiwen on 2019/6/1.
 */
@MyService
public class UserService {

    public String get(String name) {
        return "qiwen";
    }
}
