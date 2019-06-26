package com.yqw.my.db.connection.pool;

/**
 * Created by iQiwen on 2019/6/26.
 * 对连接池的一个基本管理API接口
 * 要可以得到数据库操作的管道/要可以创建数据库管道
 */
public interface IMyPool {
    MyPooledConnection getMyPooledConnection();

    void createMyPooledConnection(int count);
}
