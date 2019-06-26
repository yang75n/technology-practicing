package com.yqw.my.db.connection.pool;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by iQiwen on 2019/6/26.
 */
public class Test {

    private static IMyPool myPool = MyPoolFactory.getInstance();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            MyPooledConnection myPooledConnection = myPool.getMyPooledConnection();
            ResultSet query = myPooledConnection.query("select * from user");
            try {
                while (query.next()) {
                    System.out.println(query.getString("username") + " ,使用管道:" + myPooledConnection.getConnection());
                }
                myPooledConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
