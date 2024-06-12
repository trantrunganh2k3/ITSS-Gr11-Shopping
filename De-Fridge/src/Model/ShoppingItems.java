package Model;

public class ShoppingItems {
    private int itemID;
    private String itemName;
    private int quantity;
    private int subCost;
    private String boughtBy;
    private int listID;
    private String category;
    private String unit;

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
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
