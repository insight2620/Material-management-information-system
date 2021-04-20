package store_ma.model;

import java.util.Date;

public class ex_warehouse {
    private int goodsID;
    private int warehouseID;
    private int number;
    private Date EX_time;
    private String warehouseName;
    private String goodsName;
    private String goodsSort;
    private int goodsoutprice;

    public int getGoodsID(){return goodsID;}

    public void setGoodsID(int goodsID){this.goodsID = goodsID;}

    public int getWarehouseID(){return warehouseID;}

    public void setWarehouseID(int warehouseID){this.warehouseID = warehouseID;}

    public int getNumber(){return number;}

    public void setNumber(int number){this.number = number;}

    public Date getEX_time(){return EX_time;}

    public void setEX_time(Date ex_time){this.EX_time = ex_time;}

    public String getWarehouseName(){return warehouseName;}

    public  void setWarehouseName(String ware){this.warehouseName = ware;}

    public String getGoodsName(){return goodsName;}

    public void setGoodsName(String name){this.goodsName = name;}

    public String getGoodsSort(){return goodsSort;}

    public void setGoodsSort(String goodsSort){this.goodsSort = goodsSort;}

    public int getGoodsoutprice(){return goodsoutprice;}

    public void setGoodsoutprice(int goodsoutprice){this.goodsoutprice = goodsoutprice;}

}
