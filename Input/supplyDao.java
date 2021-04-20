package store_ma.dao;

import store_ma.model.ex_warehouse;
import store_ma.model.supply;
import store_ma.util.DbUtils;
import store_ma.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class supplyDao  extends BaseDao{
    public List<supply> getStatisticssupplyList(supply supl){
        List<supply> list = new ArrayList<>();
        String sql = "SELECT goods.name1,supplierName,supply.number,supply.confirm " +
                " From goods,supply,supplier" +
                " where goods.id = supply.goodsID and supply.supplierID = supplier.supplierId; ";
        PreparedStatement pstmt = null;
        ResultSet re = null;

        try{
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()){
                supply g = new supply();
                g.setGoodsname(re.getString("name1"));
                g.setsupplierName(re.getString("supplierName"));
                g.setNumber(re.getInt("number"));
                g.setConfirm(re.getBoolean("confirm"));
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
