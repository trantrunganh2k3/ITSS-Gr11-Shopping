package dbController;

import Model.User;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    //Add user for sign up
    public static boolean addUser(User newUser) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO public.\"user\" (username, password, gender, email, status) VALUES(?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, newUser.getUsername());
        stm.setString(2, newUser.getPassword());
        stm.setString(3, newUser.getGender());
        stm.setString(4, newUser.getEmail());
        stm.setString(5, "Active");

        return stm.executeUpdate() == 1;
    }

    //Find user for login
    public static User findUserForLogin(String username, String password) throws ClassNotFoundException, SQLException{
        String sql = "SELECT username, password, name, gender, email, address, \"groupID\", status FROM public.\"user\" WHERE username = ?";
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
                cus.setStatus(result.getString(8));
                if (result.getObject(7) != null) {
                    cus.setGroupID(result.getInt(7));
                } else {
                    cus.setGroupID(-1);
                }
                return cus;
            }
        }
        return null;
    }

    //Check signup if username is not unique
    public static boolean searchUsernameForSignup(String username) throws ClassNotFoundException, SQLException{
        String sql = "SELECT COUNT(username) FROM public.\"user\" WHERE username = ?";

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, username);
        ResultSet result = stm.executeQuery();
        int res = 0;

        while (result.next()){
            res = result.getInt(1);
        }

        return res > 0;
    }

    //Get list of users for Admin

    public static ObservableList<User> listUser(){
        ObservableList<User> userList = FXCollections.observableArrayList();
        String sql = "SELECT username, name, gender, email, address, status FROM public.\"user\"";

        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet result = stm.executeQuery();

            while (result.next()){
                userList.add(new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6) ));
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return userList;
    }

    //Update user info

    public static void updateUserInfo(User user) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"user\" SET name = ?, gender = ?, email = ?, address = ?, password = ? WHERE username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setObject(1, user.getName());
        stm.setObject(2, user.getGender());
        stm.setObject(3, user.getEmail());
        stm.setObject(4, user.getAddress());
        stm.setObject(5, user.getPassword());
        stm.setString(6, user.getUsername());

        stm.executeUpdate();
    }

    //Change groupID

    public static boolean updateUserGroupID(User user) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"user\" SET groupID = ? WHERE username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setObject(1, user.getGroupID());
        stm.setObject(2, user.getUsername());

        return stm.executeUpdate() == 1;
    }

    //Find user by username

    public static ObservableList<User> findUserByUsername(String username){
        ObservableList<User> userFind = FXCollections.observableArrayList();
        String sql = "SELECT username, name, gender, email, address, status FROM public.\"user\" WHERE username ILIKE ?";

        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setObject(1, "%" + username + "%");
            ResultSet result = stm.executeQuery();

            while (result.next()){
                userFind.add(new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6) ));
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return userFind;
    }

    //Change user status
    public static void setUserStatus(User user) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"user\" SET status = ? WHERE username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, user.getStatus());
        stm.setObject(2, user.getUsername());
        stm.executeUpdate();
    }
}
