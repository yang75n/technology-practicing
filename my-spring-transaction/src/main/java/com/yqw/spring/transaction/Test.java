package com.yqw.spring.transaction;

import javax.sql.DataSource;

/**
 * 主要是模拟Spring的注入以及多用户并发请求
 * 你可以发现，一个线程中的一个事务的多个操作，使用的是同一个Connection！
 */
public class Test {

    private static String jdbcURL = "jdbcLmysql://127.0.0.1:3307/test";
    private static String jdbcDriver = "com.mysql.jdbc.Driver";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "admin";

    public static void main(String[] args) {
        DataSource dataSource = null; //new BaseDataSource();
        final UserService userService = new UserService(dataSource);

        //模拟用户并发请求
        for (int i = 0; i < 10; i++) {
            new Thread(() ->
                    userService.action()
            ).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
