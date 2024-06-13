package Model;

import java.util.List;

public class ShoppingList {
    private int listID;
    private String listName;
    private int totalCost;
    private String date;

    private boolean shared = false;

    private List<ShoppingItems> shoppingItems;

    public ShoppingList(){}

    public ShoppingList(int listID, String listName, int totalCost, String date){
        this.listID = listID;
        this.listName = listName;
        this.totalCost = totalCost;
        this.date = date;
    }

    public void setShoppingItems(List<ShoppingItems> list){
        this.shoppingItems = list;
    }

    public boolean isShared(){
        return shared;
    }

    public void share() {
        this.shared = true;
    }

    public List<ShoppingItems> getShoppingItems() {
        return shoppingItems;
    }
    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
