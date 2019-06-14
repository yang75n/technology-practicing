package com.yqw.order.sso.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Qiwen on 2019/6/14.
 */
@Controller
@RequestMapping("/user")
public class OrderController {

    @ResponseBody
    @RequestMapping("/wel")
    public String wel() {
        return "Welcome";
    }
}
