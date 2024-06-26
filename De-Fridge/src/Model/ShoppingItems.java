package Model;

import java.sql.Date;

public class ShoppingItems {
    private int itemID;
    private String itemName;
    private double quantity = 0;
    private int subCost;
    private String boughtBy;
    private int listID;
    private String category;
    private String unit;

    private Date purchaseDay;

    public ShoppingItems(){}

    public ShoppingItems(int itemID, String itemName, int quantity, int subCost, String boughtBy, int listID, String category, String unit){
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.subCost = subCost;
        this.boughtBy = boughtBy;
        this.listID = listID;
        this.category = category;
        this.unit = unit;
    }

    public ShoppingItems(int itemID, String itemName, double quantity, String boughtBy, int listID, String category, String unit, Date date){
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.boughtBy = boughtBy;
        this.listID = listID;
        this.category = category;
        this.unit = unit;
        this.purchaseDay = date;
    }

    public void setPurchaseDay(Date date) {
        this.purchaseDay = date;
    }

    public Date getPurchaseDay () {
        return this.purchaseDay;
    }
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getSubCost() {
        return subCost;
    }

    public void setSubCost(int subCost) {
        this.subCost = subCost;
    }

    public String getBoughtBy() {
        return boughtBy;
    }

    public void setBoughtBy(String boughtBy) {
        this.boughtBy = boughtBy;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
