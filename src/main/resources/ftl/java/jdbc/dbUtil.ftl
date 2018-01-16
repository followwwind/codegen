package com.wind.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
* 数据库工具类
* jdbc操作步骤
* 1.加载类驱动  Class.forName("com.mysql.jdbc.Driver");
* 2.创建链接
* 3.创建statement
* 4.执行sql语句
* 5.关闭连接(因为数据库的连接数是有限的，释放资源)
* @author wind
*
*/
public class DbUtil {

    public static Properties props;
    /**
     * 代码静态块，在DbUtil被引用时加载执行
     * 此处用于解析数据库配置文件
     */
    static {
        props = PropUtil.readProp(DbUtil.class.getResourceAsStream("/jdbc.properties"));
        try {
            Class.forName(props.getProperty("driverClass"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
    * 获取数据库连接connection
    * @return
    */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(props.getProperty("jdbcUrl"), props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
    * 关闭数据库连接
    * @param conn
    */
    public static void close(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
