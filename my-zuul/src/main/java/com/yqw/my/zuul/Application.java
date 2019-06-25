package com.yqw.my.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在不使用网关的情况下，我们的服务是直接暴露给服务调用方。当调用方增多，势必需要添加定制化访问权限、校验等逻辑。当添加API网关后，再第三方调用端和服务提供方之间就创建了一面墙，这面墙直接与调用方通信进行权限控制。
 * 本文所实现的网关源码抄袭了---Oh,不对，是借鉴。借鉴了Zuul网关的源码，提炼出其核心思路，实现了一套简单的网关源码，博主将其改名为Eatuul。
 * 设计思路
 * 先大致说一下，就是定义一个Servlet接收请求。然后经过preFilter(封装请求参数),routeFilter(转发请求)，postFilter(输出内容)。三个过滤器之间，共享request、response以及其他的一些全局变量。如下图所示
 * image
 * 和真正的Zuul的区别?
 * 主要区别有如下几点
 * (1)Zuul中在异常处理模块，有一个ErrorFilter来处理，博主在实现的时候偷懒了，略去。
 * (2)Zuul中PreFilters,RoutingFilters,PostFilters默认都实现了一组，具体如下表所示
 * image
 * 博主总不可能每一个都给你们实现一遍吧。所以偷懒了，每种只实现一个。但是调用顺序还是不变，按照PreFilters->RoutingFilters->PostFilters的顺序调用
 * (3)在routeFilters确实有转发请求的Filter,然而博主偷天换日了，改用RestTemplate实现.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
