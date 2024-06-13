package Model;

import java.sql.Date;

public class Ingredient {
    private int ingredientID;
    private String name;
    private String category;
    private double quantity;
    private String unit;
    private Date purchaseDay;
    private Date expiryDay;
    private int fridgeID;

    public Ingredient(){}

    public Ingredient(int ingredientID, String name, String category, double quantity, String unit, Date purchaseDay, Date expiryDay, int fridgeID){
        this.ingredientID = ingredientID;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
        this.purchaseDay = purchaseDay;
        this.expiryDay = expiryDay;
        this.fridgeID = fridgeID;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(int ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getPurchaseDay() {
        return purchaseDay;
    }

    public void setPurchaseDay(Date purchaseDay) {
        this.purchaseDay = purchaseDay;
    }

    public Date getExpiryDay() {
        return expiryDay;
    }

    public void setExpiryDay(Date expiryDay) {
        this.expiryDay = expiryDay;
    }

    public int getFridgeID() {
        return fridgeID;
    }

    public void setFridgeID(int fridgeID) {
        this.fridgeID = fridgeID;
    }
}
