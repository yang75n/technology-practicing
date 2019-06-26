package com.yqw.my.tomcat;

import java.io.IOException;

/**
 * Created by iQiwen on 2019/6/26.
 */
public class FirstServlet extends MyServlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write("get FirstServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        try {
            response.write("post FirstServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
