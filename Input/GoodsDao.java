package store_ma.dao;

import store_ma.model.goods;
import store_ma.util.DbUtils;
import store_ma.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends BaseDao{
    //将界面传来的内容录入数据库
    public boolean addGoods(goods goods) {

        //定于sql语句
        String sql = "insert into goods values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            //给问号进行赋值
            pstmt.setInt(1, goods.getId());
            pstmt.setString(2, goods.getName());
            pstmt.setString(3, goods.getSort());
            pstmt.setInt(4, goods.getNumber());
            pstmt.setDouble(5, goods.getInputPrice());
            pstmt.setDouble(6, goods.getOutputPrice());
            pstmt.setDate(7,goods.getDateTime());
            pstmt.setInt(8, goods.getWarehouse_id());
            pstmt.setInt(9, goods.getSupplier_id());
            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn);
        }
        return false;
    }

    //删除货物数据库里的物品
    public boolean delete(int id) {
        String sql = "delete from goods where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            int i = pstmt.executeUpdate();
            if(i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<goods> getGoodsList(goods goods) {
        List<goods> list = new ArrayList<>();
        String sql = "select * from goods";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        if (!StringUtil.isEmpty(goods.getName())) {
            sql += " where name like '%"+goods.getName()+"%'";
        }
        try {
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()) {
                goods g = new goods();
                g.setId(re.getInt("id"));
                g.setName(re.getString("name1"));
                g.setNumber(re.getInt("number"));
                g.setInputPrice(re.getDouble("inputPrice"));
                g.setOutputPrice(re.getDouble("outputPrice"));
                g.setDateTime(re.getDate("date_of_entry"));
                g.setWarehouse_id(re.getInt("warehouse_id"));
                g.setSupplier_id(re.getInt("supplier_id"));
                list.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }

        return list;
    }
    //判断是否还能继续添加商品，
    //是否达到goods的数量,达到了则不能再添加了
    public boolean addEnable(int goodsId,int selectedNum) {
        PreparedStatement pstmt = null;
        ResultSet re = null;

        String sql = "select * from goods where id = ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,goodsId);
            //执行sql
            re = pstmt.executeQuery();
            if(re.next()){
                int number = re.getInt("number");
                if(selectedNum > number) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
//	            DbUtils.close(pstmt,conn,re);
        }
        //默认可以添加
        return true;
    }

    //让goods表中的number减少
    public boolean updateGoodsNumber(int num,int goodsId) {
        String sql = "update goods set number = number - ? where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.setInt(2, goodsId);
            if(pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }	finally {
            DbUtils.close(pstmt, conn);
        }
        return false;
    }

    public boolean update(goods goods) {
        String sql = "update goods set warehouse_id =  ? where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            System.out.println(goods.getWarehouse_id());
            System.out.println(goods.getId());
            pstmt.setInt(1, goods.getWarehouse_id());
            pstmt.setInt(2, goods.getId());
            if(pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }	finally {
            DbUtils.close(pstmt, conn);
        }
        return false;
    }

    public List<goods> getMainFrameGoodsList(goods goods){
        List<goods> list = new ArrayList<>();
        String sql = "select goods.id,goods.name1,sort,number ," +
                "                inputPrice,outputPrice,date_of_entry,name,supplierName from goods ,warehouse,supplier" +
                "                where goods.warehouse_id = warehouse.id and goods.supplier_id = supplierId";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        if (!StringUtil.isEmpty(goods.getName())) {
            sql += " where name like '%"+goods.getName()+"%'";
        }
        try {
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()) {
                goods g = new goods();
                g.setId(re.getInt("id"));
                g.setName(re.getString("name1"));
                g.setSort(re.getString("Sort"));
                g.setNumber(re.getInt("number"));
                g.setInputPrice(re.getDouble("inputPrice"));
                g.setOutputPrice(re.getDouble("outputPrice"));
                g.setDateTime(re.getDate("date_of_entry"));
                g.setWarehouse_name(re.getString("name"));
                g.setSupplier_name(re.getString("supplierName"));
                list.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }

    public List<goods> getwarehouselist(goods goods){
        List<goods> list = new ArrayList<>();
        String sql = "select name FROM warehouse";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try {
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()) {
                goods g = new goods();
                g.setWarehouse_name(re.getString("name"));
                list.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }


    public List<goods> getGoodsSortList(goods goods){
        List<goods> list = new ArrayList<>();
        String sql = "select sort FROM goods GROUP BY sort";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try {
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()) {
                goods g = new goods();
                g.setSort(re.getString("sort"));
                list.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }

    public List<goods> getsuppliernameList(goods goods){
        List<goods> list = new ArrayList<>();
        String sql = "select supplierName FROM supplier GROUP BY supplierName";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try {
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()) {
                goods g = new goods();
                g.setSupplier_name(re.getString("supplierName"));
                list.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return list;
    }

    public int getID(String goodsname){
        int goodsID = 0;
        String sql = "select id FROM goods where name1 = ?";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,goodsname);
            re = pstmt.executeQuery();
            while (re.next()) {
                goods g = new goods();
                g.setId(re.getInt("id"));
                goodsID = g.getId();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(pstmt, conn, re);
        }
        return goodsID;
    }

    public boolean updatewarehouse(int newwarehouseID,int goodsID) {
        String sql = "update goods set warehouse_id =  ? where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newwarehouseID);
            pstmt.setInt(2, goodsID);
            if(pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }	finally {
            DbUtils.close(pstmt, conn);
        }
        return false;
    }

}
