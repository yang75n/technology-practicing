package com.yqw.designPatterns.creational.singleton;

public class SingletonPatternDemo {
    public static void main(String[] args) {

        //不合法的构造函数
        //编译时错误：构造函数 SingletonObject() 是不可见的
        //SingletonObject object = new SingletonObject();

        //获取唯一可用的对象
        SingletonObject object = SingletonObject.getInstance();
        //显示消息
        object.whateverMethod();

        SingletonObject4 object4 = SingletonObject4.INSTANCE;
        object4.whateverMethod();
    }
}