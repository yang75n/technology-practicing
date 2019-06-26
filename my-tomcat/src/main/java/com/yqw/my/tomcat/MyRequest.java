package com.yqw.my.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by iQiwen on 2019/6/26.
 */
public class MyRequest {
    private String url;


    private String method;

    /**
     * 这里，你可以清楚的看到，我们通过输入流，对HTTP协议进行解析，拿到了HTTP请求头的方法以及URL。
     *
     * @param inputStream
     * @throws IOException
     */
    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestByts = new byte[1024];
        int length = 0;
        if ((length = inputStream.read(httpRequestByts)) > 0) {
            httpRequest = new String(httpRequestByts, 0, length);
        }
        //HTTP请求协议
        //GET /yqw.com HTTP/1.1
        //Accept:*/*
        //Accept-Encoding:gzip,deflate
        //User-Agent:
        //Host:
        //Connection:keep-alive
        String httpHead = httpRequest.split("\n")[0].trim();
        url = httpHead.split("\\s")[1].trim();
        method = httpHead.split("\\s")[0].trim();
        System.out.println(this);

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
