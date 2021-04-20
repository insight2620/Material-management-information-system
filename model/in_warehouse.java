package store_ma.model;

import java.util.Date;

public class in_warehouse {
    private int goodsID;
    private int warehouseID;
    private int number;
    private Date IN_time;
    private String warehouseName;
    private String goodsName;
    private String goodsSort;
    private int goodsinprice;
    private String supplierName;

    public int getGoodsID(){return goodsID;}

    public void setGoodsID(int goodsID){this.goodsID = goodsID;}

    public int getWarehouseID(){return warehouseID;}

    public void setWarehouseID(int warehouseID){this.warehouseID = warehouseID;}

    public int getNumber(){return number;}

    public void setNumber(int number){this.number = number;}

    public Date getIN_time(){return IN_time;}

    public void setIN_time(Date in_time){this.IN_time = in_time;}

    public String getWarehouseName(){return warehouseName;}

    public  void setWarehouseName(String ware){this.warehouseName = ware;}

    public String getGoodsName(){return goodsName;}

    public void setGoodsName(String name){this.goodsName = name;}

    public String getGoodsSort(){return goodsSort;}

    public void setGoodsSort(String goodsSort){this.goodsSort = goodsSort;}

    public int getGoodsinprice(){return goodsinprice;}

    public void setGoodsinprice(int goodsoutprice){this.goodsinprice = goodsoutprice;}

    public String getSupplierName(){return supplierName;}

    public  void setSupplierName(String supp){this.supplierName = supp;}
}
