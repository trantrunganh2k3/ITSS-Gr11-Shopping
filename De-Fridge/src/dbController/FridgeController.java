package dbController;

import Model.Fridge;
import Model.Model;
import Model.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FridgeController {

    public static Fridge getFridge (User user) throws SQLException, ClassNotFoundException {
        Fridge fridge = new Fridge();
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement statement;
        if (user.getGroupID() == -1) {
            String sql = "SELECT \"fridgeID\" FROM public.\"Fridge\" WHERE username = ? ";
            statement = conn.prepareStatement(sql);
            statement.setObject(1, Model.getInstance().getUser().getUsername());
        } else {
            String sql = "SELECT \"fridgeID\" FROM public.\"Fridge\" WHERE \"groupID\" = ? ";
            statement = conn.prepareStatement(sql);
            statement.setObject(1, Model.getInstance().getUser().getGroupID());
        }
        ResultSet result = statement.executeQuery();
        if(result.next()){
            fridge.setFridgeID(result.getInt(1));
            return fridge;
        }
        return null;
    }

    public static void addFridge (User user) throws SQLException, ClassNotFoundException {
        String countSql = "SELECT COUNT(\"fridgeID\") FROM public.\"Fridge\"";
        String sql = "INSERT into public.\"Fridge\" (\"fridgeID\", username) values(?,?)  ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement countStmt = conn.prepareStatement(countSql);
        ResultSet resultSet = countStmt.executeQuery();
        int res = 0;
        if (resultSet.next()) {
            res = resultSet.getInt(1);
        }

        PreparedStatement insertStmt = conn.prepareStatement(sql);
        insertStmt.setInt(1,res +1);
        insertStmt.setString(2,user.getUsername());
        insertStmt.executeUpdate();
    }
}
