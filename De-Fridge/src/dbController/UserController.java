package dbController;

import Model.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserController {
    public static boolean addUser(User newUser) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO public.\"user\" (username, password, name, gender, email, address) VALUES(?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, newUser.getUsername());
        stm.setString(2, newUser.getPassword());
        stm.setString(3, newUser.getName());
        stm.setString(4, newUser.getGender());
        stm.setString(5, newUser.getEmail());
        stm.setString(6, newUser.getAddress());

        return stm.executeUpdate() == 1;
    }
}
