package store_ma.dao;

import store_ma.model.goods;
import store_ma.model.supplier;
import store_ma.util.DbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class supplierDao extends BaseDao {
    public List<supplier> getsupplierList(supplier sup){
        List<supplier> list = new ArrayList<>();
        String sql = "SELECT supplierId,supplierName,supplierAddress,supplierPhone From supplier";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                supplier s = new supplier();
                s.setSupplierId(re.getInt("supplierId"));
                s.setSupplierName(re.getString("supplierName"));
                s.setSupplierAddress(re.getString("supplierAddress"));
                s.setSupplierPhone(re.getInt("supplierPhone"));
                list.add(s);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }
    //Ìí¼Ó
    public Boolean insert(int id,String name,String address,int phone) {
        String sql = "insert into supplier values(?,?,?,?)" ;
        PreparedStatement pstmt =null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,address);
            pstmt.setInt(4,phone);
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
        String sql = "delete from supplier where supplierId = ?";
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
        String sql = "delete from supplier where supplierId = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally { }
        return false;
    }

    public int getID(String supplierName){
        int supplierID = 0;
        String sql = "select supplierId FROM supplier where supplierName = ?";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,supplierName);
            re = pstmt.executeQuery();
            while (re.next()) {
                supplier g = new supplier();
                g.setSupplierId(re.getInt("supplierId"));
                supplierID = g.getSupplierId();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return supplierID;
    }
}
