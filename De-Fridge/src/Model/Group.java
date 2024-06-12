package Model;

public class Group {
    private int groupID;
    private String groupName;
    private String leaderUN;
    private String createDate;

    public Group(){}

    public Group(int groupID, String groupName, String leaderUN, String createDate){
        this.groupID = groupID;
        this.groupName = groupName;
        this.leaderUN = leaderUN;
        this.createDate = createDate;
    }
    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLeaderUN() {
        return leaderUN;
    }

    public void setLeaderUN(String leaderUN) {
        this.leaderUN = leaderUN;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
