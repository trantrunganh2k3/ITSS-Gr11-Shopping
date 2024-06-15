package dbController;

import Model.ShoppingList;
import db.DBConnection;

import java.sql.*;

public class ShoppingListController {
    public static int getShoppingID(String username, Date date) throws ClassNotFoundException, SQLException {
        String sql = "SELECT \"listID\" FROM public.\"ShoppingList\" WHERE date = ? AND username = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(2, username);
        stm.setDate(1, date);

        ResultSet result = stm.executeQuery();
        int res = 0;
        while (result.next()){
            res = result.getInt(1);
        }
        return res;
    }

    public static boolean addShoppingList(ShoppingList pList) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO public.\"ShoppingList\" (\"listID\", \"listName\", date)" +
                "VALUES(?, ?, ?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, pList.getListID());
        stm.setString(2, pList.getListName());
        stm.setDate(3, pList.getDate());

        return stm.executeUpdate() == 1;
    }

    public static boolean deleteShoppingList(ShoppingList pList) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM public.\"ShoppingList\" WHERE \"listID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, pList.getListID());

        return stm.executeUpdate() == 1;
    }
}
