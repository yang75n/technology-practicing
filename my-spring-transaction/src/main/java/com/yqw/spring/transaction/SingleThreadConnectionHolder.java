package com.yqw.spring.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 本来线程不安全的，通过ThreadaLocal这么封装一下，
 * 立刻就变成了线程的局部变量，不仅仅安全了，
 * 还保证了一个线程下面的操作拿到的Connection是同一个对象！这种思想，
 * 确实非常巧妙，这也是无锁编程思想的一种方式！
 */
public class SingleThreadConnectionHolder {
    private static ThreadLocal<ConnectionHolder> threadLocal = new ThreadLocal<ConnectionHolder>();

    private static ConnectionHolder getConnectHolder() {
        ConnectionHolder connectionHolder = threadLocal.get();

        if (connectionHolder == null) {
            connectionHolder = new ConnectionHolder();
            threadLocal.set(connectionHolder);
        }

        return connectionHolder;
    }

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        return getConnectHolder().getConnectionByDataSource(dataSource);
    }
}
