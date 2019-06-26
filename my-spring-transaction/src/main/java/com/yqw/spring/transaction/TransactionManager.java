package com.yqw.spring.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * TransactionManager，这个我们经常在Spring里面进行配置吧，事务大管家！
 */
public class TransactionManager {
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }


    //开启事务
    public void start() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    //回滚事务
    public void rollback() throws SQLException {
        Connection connection = getConnection();
        connection.rollback();
    }

    //关闭事务
    public void close() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        connection.close();
    }

}
