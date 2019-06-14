package com.yqw.sso.server;

import com.yqw.sso.server.interceptor.SessionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Qiwen on 2019/6/14.
 */
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor()).addPathPatterns("/user/*");
    }

    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }
}
