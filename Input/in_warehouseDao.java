package store_ma.dao;

import store_ma.model.NP;
import store_ma.model.ex_warehouse;
import store_ma.model.in_warehouse;
import store_ma.util.DbUtils;
import store_ma.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class in_warehouseDao extends BaseDao{
    public List<in_warehouse> getin_warehouseList(in_warehouse in_ware){
        List<in_warehouse> list = new ArrayList<>();
        String sql = "SELECT goods.name1,goods.sort,in_warehouse.number,goods.inputPrice,warehouse.name,in_warehouse.IN_time,supplier.supplierName" +
                "                From goods,in_warehouse,warehouse,supplier" +
                "                where goods.id = in_warehouse.goodsID and in_warehouse.warehouseID = warehouse.id and in_warehouse.supplier_id= supplier.supplierId;";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        if(!StringUtil.isEmpty(in_ware.getWarehouseName()))
        {
            return null;
        }
        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                in_warehouse g = new in_warehouse();
                g.setGoodsName(re.getString("name1"));
                g.setGoodsSort(re.getString("sort"));
                g.setNumber(re.getInt("number"));
                g.setGoodsinprice(re.getInt("inputPrice"));
                g.setWarehouseName(re.getString("name"));
                g.setIN_time(re.getDate("IN_time"));
                g.setSupplierName(re.getString("supplierName"));
                list.add(g);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }

    public boolean addin_warerecord(int goodsID,int warehouseID,int number,String date,int supplierID){
        String sql = "insert into in_warehouse " +
                "VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,goodsID);
            pstmt.setInt(2,warehouseID);
            pstmt.setInt(3,number);
            pstmt.setString(4,date);
            pstmt.setInt(5,supplierID);
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

    public List<NP> getinNP(String sql){
        List<NP> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                NP g = new NP();
                g.setNumber(re.getInt("SUM(in_warehouse.number)"));
                g.setPrice(re.getInt("(SUM(in_warehouse.number)*goods.inputPrice)"));
                //JOptionPane.showMessageDialog(null, String.valueOf(re.getDate("Ex_time")));
                list.add(g);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }

    public List<in_warehouse> checkin_warehouseList(String sql){
        List<in_warehouse> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                in_warehouse g = new in_warehouse();
                g.setGoodsName(re.getString("name1"));
                g.setGoodsSort(re.getString("sort"));
                g.setNumber(re.getInt("number"));
                g.setGoodsinprice(re.getInt("inputPrice"));
                g.setWarehouseName(re.getString("name"));
                g.setIN_time(re.getDate("IN_time"));
                //JOptionPane.showMessageDialog(null, String.valueOf(re.getDate("Ex_time")));
                list.add(g);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }
}
