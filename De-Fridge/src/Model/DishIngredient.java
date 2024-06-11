package Model;

public class DishIngredient {
    private int dishID;
    private String ingredientName;
    private double quantity = 0;
    private String unit;

    private int ingredientId;

    public void setIngredientId (int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getIngredientId () {
        return this.ingredientId;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
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
}
