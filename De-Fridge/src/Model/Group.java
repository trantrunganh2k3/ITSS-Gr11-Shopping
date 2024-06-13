package Model;

import java.sql.Date;

public class Group {
    private int groupID;
    private String groupName;
    private String leaderUN;
    private Date createDate;

    public Group(){}

    public Group(int groupID, String groupName, String leaderUN, Date createDate){
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
