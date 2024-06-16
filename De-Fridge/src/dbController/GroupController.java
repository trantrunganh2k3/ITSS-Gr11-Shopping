package dbController;

import Model.Group;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupController {

    public static boolean addUserInGroup(String username, int groupID) throws ClassNotFoundException, SQLException{
        String sql = "Update public.\"user\" SET \"groupID\" = ? WHERE username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, groupID);
        stm.setString(2, username);

        return stm.executeUpdate() == 1;
    }

    public static boolean deleteUserInGroup(String username) throws ClassNotFoundException, SQLException{
        String sql = "Update public.\"user\" SET \"groupID\" = null WHERE username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, username);

        return stm.executeUpdate() == 1;
    }

    public static boolean addGroup(Group group) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO public.\"Group\" (\"groupID\", \"groupName\", \"leaderUN\", \"createDate\")" +
                "VALUES(?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, group.getGroupID());
        stm.setString(2, group.getGroupName());
        stm.setString(3, group.getLeaderUN());
        stm.setDate(4, group.getCreateDate());

        return stm.executeUpdate() == 1;
    }

    public static boolean updateLeader(String username, int groupID) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"Group\" SET leaderUN = ? WHERE \"groupID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(2, groupID);
        stm.setString(1, username);

        return stm.executeUpdate() == 1;
    }
}
