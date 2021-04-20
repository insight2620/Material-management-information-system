package store_ma.util;

import java.sql.*;

/**
 *@author ZhouJian
 *
 * 与数据库连接
 */
public class DbUtils {

    //连接地址
    private String dbUrl = "jdbc:mysql://localhost:3306/store_m?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useOldAliasMetadataBehavior=true&allowPublicKeyRetrieval=true&useOldAliasMetadataBehavior=true&useSSL=false";
    //用户名
    private String dbUserName = "root";
    //密码
    private String dbPassword = "25802580";
    //驱动名称
    private String jdbcName = "com.mysql.jdbc.Driver";

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConn(){

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库
     * @param conn
     */
    public static void close(PreparedStatement pstmt ,Connection conn,ResultSet re){

        if (re != null){
            try {
                re.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //方法重载
    public static void close(PreparedStatement pstmt ,Connection conn){
        if (pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

