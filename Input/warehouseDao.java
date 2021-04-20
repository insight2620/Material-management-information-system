package store_ma.dao;

import store_ma.model.warehouse;
import store_ma.util.DbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class warehouseDao extends BaseDao {
    public int getID(String warehouse){
        int warehouseID = 0;
        String sql = "select id FROM warehouse where  warehouse.`name` =  ?";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,warehouse);
            re = pstmt.executeQuery();
            while (re.next()) {
                warehouse g = new warehouse();
                g.setId(re.getInt("id"));
                warehouseID = g.getId();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return warehouseID;
    }

    public List<warehouse> getwarehouseList(warehouse wareh){
        List<warehouse> list = new ArrayList<>();
        String sql = "SELECT id,name,area From warehouse";
        PreparedStatement pstmt = null;
        ResultSet re = null;

        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                warehouse w = new warehouse();
                w.setId(re.getInt("id"));
                w.setName(re.getString("name"));
                w.setArea(re.getString("area"));
                list.add(w);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }
    //Ìí¼Ó
    public Boolean insert(int id,String name,String area) {
        String sql = "insert into warehouse values(?,?,?)" ;
        PreparedStatement pstmt =null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,area);
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(pstmt,conn);
        }
        return false;
    }
    //É¾³ý
    public boolean delete(int id){
        String sql = "delete from warehouse where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(pstmt,conn);
        }
        return false;
    }
    public boolean delete1(int id){
        String sql = "delete from warehouse where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return false;
    }
}
