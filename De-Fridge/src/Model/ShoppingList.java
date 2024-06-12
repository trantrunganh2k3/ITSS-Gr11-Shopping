package Model;

public class ShoppingList {
    private int listID;
    private String listName;
    private int totalCost;
    private String date;

    public ShoppingList(){}

    public ShoppingList(int listID, String listName, int totalCost, String date){
        this.listID = listID;
        this.listName = listName;
        this.totalCost = totalCost;
        this.date = date;
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
