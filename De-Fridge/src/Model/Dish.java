package Model;

public class Dish {
    private int dishID;
    private String name;
    private String meal;
    private String forDate;
    private String username;
    private String fridgeID;

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

    public String getForDate() {
        return forDate;
    }

    public void setForDate(String forDate) {
        this.forDate = forDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFridgeID() {
        return fridgeID;
    }

    public void setFridgeID(String fridgeID) {
        this.fridgeID = fridgeID;
    }
}
