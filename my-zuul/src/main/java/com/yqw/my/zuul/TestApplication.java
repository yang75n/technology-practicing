package com.yqw.my.zuul;

import com.yqw.my.zuul.testController.IndexController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 如何测试？
 * 自己另外起一个server端口为9090如下所示.
 * 然后，你就发现可以从localhost:8080/index进行跳转访问了
 */
@SpringBootApplication
@ServletComponentScan(basePackageClasses = IndexController.class)
public class TestApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).properties("server.port=9090").run(args);
    }
}
