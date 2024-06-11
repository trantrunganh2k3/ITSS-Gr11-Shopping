package dbController;

import Model.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static User findUserForLogin(String username, String password) throws ClassNotFoundException, SQLException{
        String sql = "SELECT username, password, name, gender, email, address, groupID FROM public.\"user\" WHERE username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setObject(1, username);
        ResultSet result = statement.executeQuery();

        if(result.next()){
            String usernameResult = result.getString(1);
            String pass = result.getString(2);
            if(usernameResult.equals(username) && pass.equals(password)){
                User cus = new User();
                cus.setUsername(result.getString(1));
                cus.setPassword(result.getString(2));
                cus.setName(result.getString(3));
                cus.setGender(result.getString(4));
                cus.setEmail(result.getString(5));
                cus.setAddress(result.getString(6));
                cus.setGroupID(result.getInt(7));
                return cus;
            }
        }
        return null;
    }
}
