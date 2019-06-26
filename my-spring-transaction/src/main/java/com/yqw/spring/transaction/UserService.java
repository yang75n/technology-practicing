package com.yqw.spring.transaction;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 这里通过这2个DAO，想模拟一个事务中账户购买、下单2个操作。
 * 到这里，可以清晰的看到Spring事务管理的一个缩影了吧！
 */
public class UserService {
    private UserAccountDao userAccountDao;
    private UserOrderDao userOrderDao;
    private TransactionManager transactionManager;

    public UserService(DataSource dataSource) {
        userAccountDao = new UserAccountDao(dataSource);
        userOrderDao = new UserOrderDao(dataSource);
        transactionManager = new TransactionManager(dataSource);
    }

    //模拟一个事务中账户购买和下单2个操作
    public void action() {
        try {
            transactionManager.start();
            userAccountDao.buy();
            userOrderDao.order();
            transactionManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
