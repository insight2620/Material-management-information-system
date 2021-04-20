package store_ma.model;

public class supply {
    private int supplierID;
    private int goodsID;
    private int number;
    private boolean confirm;
    private String goodsname;
    private String supplierName;

    public int getSupplierID(){return supplierID;}

    public void setSupplierID(int supplierID){this.supplierID = supplierID; }

    public int getGoodsID(){return goodsID;}

    public void setGoodsID(int goodsID){this.goodsID = goodsID;}

    public int getNumber(){return number;}

    public void setNumber(int number){this.number = number;}

    public boolean getconfirm(){return confirm;}

    public void setConfirm(boolean confirm){this.confirm = confirm;}

    public String getGoodsname(){return goodsname;}

    public void setGoodsname(String goodsname){this.goodsname = goodsname;}

    public String getsupplierName(){return supplierName;}

    public void setsupplierName(String supplierName){this.supplierName = supplierName;}

}
