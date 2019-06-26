package com.yqw.my.db.connection.pool;

/**
 * Created by iQiwen on 2019/6/26.
 * 我们在实际中使用数据库连接池，需要在Spring的配置文件中，进行一些参数配置。
 * 这里，为了简化解析，直接提供。
 * <p/>
 * <p/>
 * 很多数据库连接池都需要XML或者properties进行配置
 * 这里为了简化解析过程，直接这样听歌常量
 */
public class DBConfigXML {
    public static final String jdbcDriver = "com.mysql.jdbc.Driver";
    public static final String jdbcURL = "jdbc:mysql://127.0.0.1:3307/test";
    public static final String jdbcUsername = "root";
    public static final String jdbcPassword = "admin";

    //数据库连接池初始化大小
    public static final int initCount = 10;

    //连接池不足的时候增长的步长
    public static final int step = 2;

    //连接池最大的数量
    public static final int maxCount = 50;

}
