package store_ma.model;

import java.sql.Date;

public class goods {
    private int id; //货物id
    private String name; //商品名
    private String sort;//类型
    private int number; //商品数量
    private double inputPrice; // 进货价
    private double outputPrice; //出货价
    private Date date_of_entry; //商品输入日期
    private int supplier_id; //供应商id
    private int warehouse_id; //仓库id
    private String warehouse_name;
    private String supplier_name;


    public int getSupplier_id() {
        return supplier_id;
    }
    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }
    public int getWarehouse_id() {
        return warehouse_id;
    }
    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }
    public Date getDateTime() {
        return date_of_entry;
    }
    public void setDateTime(Date date_of_entry) {
        this.date_of_entry = date_of_entry;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSort(){return sort;}
    public void setSort(String sort){this.sort = sort;}

    public double getInputPrice() {
        return inputPrice;
    }
    public void setInputPrice(double inputPrice) {
        this.inputPrice = inputPrice;
    }
    public double getOutputPrice() {
        return outputPrice;
    }
    public void setOutputPrice(double outputPrice) {
        this.outputPrice = outputPrice;
    }

    public String getWarehouse_name(){return warehouse_name;}
    public void setWarehouse_name(String warehouse_name){this.warehouse_name=warehouse_name;}
    public String getSupplier_name(){return supplier_name;}
    public void setSupplier_name(String supplier_name){this.supplier_name=supplier_name;}

    @Override
    public String toString() {
        return this.name;
    }
}
