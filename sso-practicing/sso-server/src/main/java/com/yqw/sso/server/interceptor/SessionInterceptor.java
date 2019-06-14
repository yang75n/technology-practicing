package com.yqw.sso.server.interceptor;

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
        //在user.com中判断是否有session对象
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null && httpSession.getAttribute("isLogin").equals("logined")) {
            return true;
        }


        return false;
    }
}
