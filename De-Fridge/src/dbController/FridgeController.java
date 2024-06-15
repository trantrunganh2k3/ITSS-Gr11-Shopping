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
            String sql = "SELECT \"fridegID\" FROM public.\"Fridge\" WHERE username = ? ";
            statement = conn.prepareStatement(sql);
            statement.setObject(1, Model.getInstance().getUser().getUsername());
        } else {
            String sql = "SELECT \"fridegID\" FROM public.\"Fridge\" WHERE \"groupID\" = ? ";
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
}
