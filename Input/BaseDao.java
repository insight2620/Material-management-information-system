package store_ma.dao;



import store_ma.util.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *@author ZhouJian
 *
 * �������ݿ����Ӷ���������Ŀ�����ݿ�򽻵�������һ������
 */
public class BaseDao {

    public Connection conn = new DbUtils().getConn();

    //�ر���Դ
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
