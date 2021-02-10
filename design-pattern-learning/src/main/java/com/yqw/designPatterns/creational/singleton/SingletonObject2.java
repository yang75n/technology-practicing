package com.yqw.designPatterns.creational.singleton;

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 * JDK 版本：JDK1.5 起
 * <p>
 * 是否 Lazy 初始化：是
 * <p>
 * 是否多线程安全：是
 * <p>
 * 实现难度：较复杂
 * <p>
 * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * getInstance() 的性能对应用程序很关键。
 */
public class SingletonObject2 {

    private volatile static SingletonObject2 singleton;

    private SingletonObject2() {
    }

    public static SingletonObject2 getSingleton() {
        if (singleton == null) {
            synchronized (SingletonObject2.class) {
                if (singleton == null) {
                    singleton = new SingletonObject2();//加volatile是因为构造此对象的时候需要一个过程
                    //，防止另外一个线程活到一个没有初始化完成的对象.，因为volatile防止了指令重排序，
                    // 从而防止了此问题发生，synchronized也可以放置指令重排序，但此处的synchronized是加载class上面的
                    //只能防止SingletonObject2.class相关代码不指令重排，不能防止singleton相关代码的指令重排序
                }
            }
        }
        return singleton;
    }
}