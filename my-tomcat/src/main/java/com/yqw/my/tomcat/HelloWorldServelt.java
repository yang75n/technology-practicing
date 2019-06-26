package com.yqw.my.tomcat;

import java.io.IOException;

/**
 * Created by iQiwen on 2019/6/26.
 */
public class HelloWorldServelt extends MyServlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write("get HelloWorldServelt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        try {
            response.write("post HelloWorldServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
