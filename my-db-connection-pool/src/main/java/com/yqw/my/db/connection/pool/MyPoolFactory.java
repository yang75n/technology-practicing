package com.yqw.my.db.connection.pool;

/**
 * Created by iQiwen on 2019/6/26.
 * 单例模式
 * 数据库连接池工厂
 */
public class MyPoolFactory {
    private static class CreatePool {
        public static IMyPool myPool = new MyDefaultPool();
    }

    public static IMyPool getInstance() {
        return CreatePool.myPool;
    }
}
