package store_ma.dao;

import store_ma.model.NP;
import store_ma.util.DbUtils;
import store_ma.util.StringUtil;
import store_ma.model.ex_warehouse;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ex_warehouseDao extends BaseDao {
    public List<ex_warehouse> getex_warehouseList(ex_warehouse ex_ware){
        List<ex_warehouse> list = new ArrayList<>();
        String sql = "SELECT goods.name1,goods.sort,ex_warehouse.number,goods.outputPrice,warehouse.name,ex_warehouse.Ex_time " +
                " From goods,ex_warehouse,warehouse" +
                " where goods.id = ex_warehouse.goodsID and ex_warehouse.warehouseID = warehouse.id; ";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        if(!StringUtil.isEmpty(ex_ware.getWarehouseName()))
        {
            return null;
        }
        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                ex_warehouse g = new ex_warehouse();
                g.setGoodsName(re.getString("name1"));
                g.setGoodsSort(re.getString("sort"));
                g.setNumber(re.getInt("number"));
                g.setGoodsoutprice(re.getInt("outputPrice"));
                g.setWarehouseName(re.getString("name"));
                g.setEX_time(re.getDate("Ex_time"));
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


    public boolean addex_warerecord(int goodsID,int warehouseID,int number,String date){
        String sql = "insert into ex_warehouse " +
                "VALUES(?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,goodsID);
            pstmt.setInt(2,warehouseID);
            pstmt.setInt(3,number);
            pstmt.setString(4,date);
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


    public List<ex_warehouse> checkex_warehouseList(String sql){
        List<ex_warehouse> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                ex_warehouse g = new ex_warehouse();
                g.setGoodsName(re.getString("name1"));
                g.setGoodsSort(re.getString("sort"));
                g.setNumber(re.getInt("number"));
                g.setGoodsoutprice(re.getInt("outputPrice"));
                g.setWarehouseName(re.getString("name"));
                g.setEX_time(re.getDate("Ex_time"));
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

    public List<NP> getexNP(String sql){
        List<NP> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                NP g = new NP();
                g.setNumber(re.getInt("SUM(ex_warehouse.number)"));
                g.setPrice(re.getInt("(SUM(ex_warehouse.number)*goods.outputPrice)"));
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
