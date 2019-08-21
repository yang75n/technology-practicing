package com.yqw.sso.server.controller;

import com.yqw.sso.server.util.JwtUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qiwen on 2019/6/14.
 */
@Controller
public class SsoController {

    private List<String> list = new ArrayList<>();


    @RequestMapping(value = "/preLogin", method = RequestMethod.GET)
    public String preLogin(String url, HttpServletRequest request, Model model) {
        System.out.println(url);
        HttpSession httpSession = request.getSession(false);
        //发现认证中心没有全局session会话
        if (httpSession == null) {
            model.addAttribute("url", url);
            //跳转至登录页面
            return "login";
        } else {
            String token = (String) httpSession.getAttribute("token");
            return "redirect:http//" + url + "?token=" + token;
        }
    }

    @RequestMapping(value = "/login")
    public String login(String username, String password, String url, HttpServletRequest request) {
        if ("qiwen".equals(username) && "123456".equals(password)) {
            String token = JwtUtil.createJwt();//生成token
            System.out.println(token);
            //创建全局sso对应的session信息
            request.getSession().setAttribute("token", token);
            list.add(token);
            return "redirect:http//" + url + "?token=" + token;
        } else {
            return "login";
        }
    }

    @RequestMapping("/checkToken")
    @ResponseBody
    public String checkToken(String token) {
        //不是伪造的
        if (list.contains(token) && JwtUtil.verifyToken(token)) {
            return "correct";
        } else {
            return "incorrect";
        }
    }
}
