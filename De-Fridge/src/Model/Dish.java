package Model;

import java.sql.Date;
import java.util.List;

public class Dish {
    private int dishID;
    private String name;
    private String meal;
    private Date forDate;
    private String username;
    private int fridgeID;

    public Dish(){}

    public Dish(int dishID, String name, String meal, String username, int fridgeID){
        this.dishID = dishID;
        this.name = name;
        this.meal = meal;
        this.username = username;
        this.fridgeID = fridgeID;
    }

    private List<DishIngredient> ingredients;

    public List<DishIngredient> getIngredients() {
        return this.ingredients;
    }
    public void setIngredients(List<DishIngredient> ingredients){
        this.ingredients = ingredients;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public Date getForDate() {
        return forDate;
    }

    public void setForDate(Date forDate) {
        this.forDate = forDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFridgeID() {
        return fridgeID;
    }

    public void setFridgeID(int fridgeID) {
        this.fridgeID = fridgeID;
    }
}
