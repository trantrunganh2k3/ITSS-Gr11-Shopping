package Model;

public class Fridge {
    private int fridgeID;
    private int groupID;
    private String username;

    public Fridge(){}

    public Fridge(int fridgeID, int groupID, String username){
        this.fridgeID = fridgeID;
        this.groupID = groupID;
        this.username = username;
    }
    public int getFridgeID() {
        return fridgeID;
    }

    public void setFridgeID(int fridgeID) {
        this.fridgeID = fridgeID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
