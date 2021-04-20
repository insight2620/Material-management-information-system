package store_ma.util;

import java.sql.*;

/**
 *@author ZhouJian
 *
 * �����ݿ�����
 */
public class DbUtils {

    //���ӵ�ַ
    private String dbUrl = "jdbc:mysql://localhost:3306/store_m?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useOldAliasMetadataBehavior=true&allowPublicKeyRetrieval=true&useOldAliasMetadataBehavior=true&useSSL=false";
    //�û���
    private String dbUserName = "root";
    //����
    private String dbPassword = "25802580";
    //��������
    private String jdbcName = "com.mysql.jdbc.Driver";

    /**
     * ��ȡ���ݿ�����
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
     * �ر����ݿ�
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

    //��������
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

