package dbController;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static String login(String username, String password){
        String sql = "SELECT username, password FROM public.\"user\" WHERE username = ?";
        try{
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1, username);
            ResultSet result = statement.executeQuery();

            if(result.next()){
                String usernameResult = result.getString(1);
                String pass = result.getString(2);
                if(usernameResult.equals(username) && pass.equals(password)){
                    if(username.equals("admin")) return "admin";
                    else return "user";
                }else return "Invalid";
            }else return null;
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
