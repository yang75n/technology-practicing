package com.yqw.my.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by iQiwen on 2019/6/26.
 */
public class MyResponse {
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * 基于HTTP协议的格式进行输出写入。
     *
     * @param content
     * @throws IOException
     */
    public void write(String content) throws IOException {
        //HTTP响应协议:
        //HTTP/1.1 200 OK
        //Content-Type:text/html
        //
        //<html><body>Hello</body></html>

        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n").append("Content-Type:text/html\n")
                .append("\r\n").append("<html><body>").append(content).append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
