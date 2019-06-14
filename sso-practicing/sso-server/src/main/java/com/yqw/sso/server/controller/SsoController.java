package com.yqw.sso.server.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Qiwen on 2019/6/14.
 */
@Controller
public class SsoController {

    @RequestMapping(value = "/preLogin", method = RequestMethod.GET)
    public String preLogin(String url, HttpServletRequest request, Model model) {
        System.out.println(url);
        HttpSession httpSession = request.getSession(false);
        //发现认证中心没有全局session会话
        if (httpSession == null) {
            model.addAttribute("url", url);
            return "login";
        } else {
            String token = (String) httpSession.getAttribute("token");
            return "redirect:http//" + url + "?token=" + token;
        }
    }
}
