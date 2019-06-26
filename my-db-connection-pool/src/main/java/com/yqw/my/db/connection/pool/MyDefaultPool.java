package com.yqw.my.db.connection.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by iQiwen on 2019/6/26.
 * 需要注意到是，MyDefaultPool持有一个管道集合，基于多线程的考虑，这里使用了Vector。
 */
public class MyDefaultPool implements IMyPool {

    private Vector<MyPooledConnection> myPooledConnectionVector = new Vector<MyPooledConnection>();
    private static String jdbcURL;
    private static String jdbcDriver;
    private static String jdbcUsername;
    private static String jdbcPassword;
    private static int initCount;
    private static int step;
    private static int maxCount;

    public MyDefaultPool() {
        //初始化数据库连接池配置
        init();

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        createMyPooledConnection(initCount);
    }

    private void init() {
        jdbcDriver = DBConfigXML.jdbcDriver;
        jdbcURL = DBConfigXML.jdbcURL;
        jdbcPassword = DBConfigXML.jdbcPassword;
        jdbcUsername = DBConfigXML.jdbcUsername;
        initCount = DBConfigXML.initCount;
        step = DBConfigXML.step;
        maxCount = DBConfigXML.maxCount;
    }


    @Override
    public MyPooledConnection getMyPooledConnection() {
        if (myPooledConnectionVector.size() < 1) {
            throw new RuntimeException("连接池初始化错误");
        }
        MyPooledConnection myPooledConnection = null;
        try {
            myPooledConnection = getRealConnectionFromPool();
            while (myPooledConnection == null) {
                createMyPooledConnection(step);
                myPooledConnection = getMyPooledConnection();
                return myPooledConnection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myPooledConnection;
    }

    /**
     * 第一，这里使用了synchronized，就是为了避免多线程下产生问题。
     * <p/>
     * 第二，要知道Connection是有超时机制的，如果我们得到的管道的Connection已经超时了怎么办呢？
     * <p/>
     * 第三，得到管道后，一定注意isBusy的设置。
     *
     * @return
     * @throws SQLException
     */
    private synchronized MyPooledConnection getRealConnectionFromPool() throws SQLException {
        for (MyPooledConnection myPooledConnection : myPooledConnectionVector) {
            if (myPooledConnection.getConnection().isValid(3000)) {
                myPooledConnection.setBusy(true);
                return myPooledConnection;
            } else {
                Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                myPooledConnection.setConnection(connection);
                myPooledConnection.setBusy(true);
                return myPooledConnection;
            }
        }
        return null;
    }

    /**
     * 数据库连接池在创建管道时，应该去看一下是否达到上限，如果没有，则可以创建。
     * <p/>
     * 不仅仅要创建出来，还要标示每一个管道的isBusy标志。
     */
    @Override
    public void createMyPooledConnection(int count) {
        if (myPooledConnectionVector.size() > maxCount || myPooledConnectionVector.size() + count > maxCount) {
            throw new RuntimeException("连接池已满");
        }
        for (int i = 0; i < count; i++) {
            try {
                Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                MyPooledConnection myPooledConnection = new MyPooledConnection(connection, false);
                myPooledConnectionVector.add(myPooledConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
