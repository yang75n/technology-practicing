package com.yqw.classpath;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        //classpath路径
        String classpath = Main.class.getClassLoader().getResource("").getPath();
        System.out.println("classpath=" + classpath);
        String path = Main.class.getClassLoader().getResource("test.txt").getPath();
        System.out.println("path=" + path);
        //当前类的相对路径,得到的是当前类class文件的URI目录。不包括自己！
        String mainPath = Main.class.getResource("").getPath();
        System.out.println("mainPath=" + mainPath);
        //得到的是当前的classpath的绝对URI路径 。
        String mainPath2 = Main.class.getResource("/").getPath();
        System.out.println("mainPath2=" + mainPath2);

        //得到的也是当前ClassPath的绝对URI路径
        String classLoadPath = ClassLoader.getSystemResource("").getPath();
        System.out.println("classLoadPath=" + classLoadPath);

        //得到的也是当前ClassPath的绝对URI路径
        Thread.currentThread().getContextClassLoader().getResource("");

        //尽量不要使用相对于System.getProperty（"user.dir"）当前用户目录的相对路径。这是一颗定时炸弹，随时可能要你的命。
        String userdir = System.getProperty("user.dir");
        System.out.println("userdir=" + userdir);


        Enumeration<URL> paths = ClassLoader.getSystemResources("test.txt");
        while (paths.hasMoreElements()) {
            System.out.println("emumpaths=" + paths.nextElement());
        }

        BeanFactory beanFactory = new AnnotationConfigApplicationContext(Main.class);
        //System.out.println(beanFactory.getBean(""));

    }
}
