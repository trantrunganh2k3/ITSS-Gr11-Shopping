package dbController;

import Model.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public static User login(String username, String password) throws ClassNotFoundException, SQLException{
        User res = UserController.findUserForLogin(username, password);
        return res;
    }
}
