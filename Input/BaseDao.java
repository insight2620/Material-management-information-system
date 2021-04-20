package store_ma.dao;



import store_ma.util.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *@author ZhouJian
 *
 * 创建数据库连接对象，整个项目与数据库打交道都用这一个对象
 */
public class BaseDao {

    public Connection conn = new DbUtils().getConn();

    //关闭资源
    public void closeDao(){
    if(conn != null){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
}
