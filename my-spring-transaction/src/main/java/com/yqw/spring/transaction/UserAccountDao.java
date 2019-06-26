package com.yqw.spring.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by iQiwen on 2019/6/26.
 */
public class UserAccountDao {
    private DataSource dataSource;

    public UserAccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void buy() throws SQLException {
        Connection connection = SingleThreadConnectionHolder.getConnection(dataSource);
        //进行buy的业务操作
        //...
        System.out.println("当前用户购买线程:" + Thread.currentThread().getName() +
                " ,使用管道（hashCode）：" + connection.hashCode());
    }
}
