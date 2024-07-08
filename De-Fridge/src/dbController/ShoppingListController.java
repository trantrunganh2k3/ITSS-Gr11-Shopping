package dbController;

import Model.*;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListController {
    public static ObservableList<ShoppingList> getShoppingList(String username, Date date, boolean isOwner) throws ClassNotFoundException, SQLException {
        ObservableList<ShoppingList> lists = FXCollections.observableArrayList();
        String sql = "SELECT public.\"ShoppingList\".\"listID\", \"listName\" FROM public.\"ShoppingList\", public.\"OwnerShip\"  WHERE date = ? AND username = ?" +
                "  AND public.\"ShoppingList\".\"listID\" = public.\"OwnerShip\".\"listID\" AND \"isOwner\" = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(2, username);
        stm.setDate(1, date);
        stm.setBoolean(3,isOwner);

        ResultSet result = stm.executeQuery();

        while (result.next()){
            ShoppingList list = new ShoppingList(result.getInt(1), result.getString(2),date, isShared(result.getInt(1)));
            list.setShoppingItems(getShoppingItems(list));
            lists.add(list);

        }
        return lists;
    }

    public static void addShoppingList(ShoppingList pList) throws ClassNotFoundException, SQLException{
        Connection conn = DBConnection.getDBConnection().getConnection();
        String countSql = "SELECT COUNT(\"listID\") FROM public.\"ShoppingList\"";
        PreparedStatement countStmt = null;
        countStmt = conn.prepareStatement(countSql);
        ResultSet resultSet = countStmt.executeQuery();
        int res = 0;

        if (resultSet.next()) {
            res = resultSet.getInt(1);
        }

        String sql = "INSERT INTO public.\"ShoppingList\" (\"listID\", \"listName\", date)" +
                "VALUES(?, ?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, res +1);
        stm.setString(2, pList.getListName());
        stm.setDate(3, pList.getDate());
        stm.executeUpdate();
        addOwner(Model.getInstance().getUser().getUsername(),res + 1, true);
    }

    public static void deleteShoppingList(ShoppingList pList) throws ClassNotFoundException, SQLException{
        deleteOwner(pList.getListID());
        String sql = "DELETE FROM public.\"ShoppingList\" WHERE \"listID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, pList.getListID());
        stm.executeUpdate();
    }

    public static ObservableList<ShoppingItems> getShoppingItems(ShoppingList list) throws SQLException, ClassNotFoundException {
        String sql = "Select * from public.\"ShoppingItems\" WHEre \"listID\" = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1,list.getListID());
        ObservableList<ShoppingItems> items = FXCollections.observableArrayList();
        ResultSet result = stm.executeQuery();
        while (result.next()){
            items.add(new ShoppingItems(result.getInt(1), result.getString(2), result.getDouble(3), result.getString(5),
            result.getInt(6), result.getString(7), result.getString(8),result.getDate(9)));
        }
        return items;
    }

    public static void deleteShoppingItems (ShoppingList list) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM public.\"ShoppingItems\" WHERE \"listID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1,list.getListID());
        stm.executeUpdate();
    }

   public static void updateShoppingList (ShoppingList list) throws SQLException, ClassNotFoundException {
        deleteShoppingItems(list);
        for (ShoppingItems item: list.getShoppingItems()){
            String sql = "INSERT INTO public.\"ShoppingItems\" (quantity, \"itemName\", \"boughtBy\", \"listID\", category, unit, \"purchaseDay\")" +
                    " VALUES(?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setDouble(1,item.getQuantity());
            stm.setString(2,item.getItemName());
            stm.setString(3,item.getBoughtBy());
            stm.setInt(4,list.getListID());
            stm.setString(5,item.getCategory());
            stm.setString(6,item.getUnit());
            stm.setDate(7,item.getPurchaseDay());
            stm.executeUpdate();
        }
   }
   public static void addOwner (String username, int res, boolean isOwner) throws SQLException, ClassNotFoundException {
       String sql = "INSERT INTO public.\"OwnerShip\" (\"listID\", \"username\", \"isOwner\")" +
               " VALUES(?,?,?)";
       Connection conn = DBConnection.getDBConnection().getConnection();
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setInt(1,res);
       stm.setString(2, username);
       stm.setBoolean(3,isOwner);
       stm.executeUpdate();
   }

   public static void deleteOwner (int id) throws SQLException, ClassNotFoundException {
       String sql = "DELETE FROM public.\"OwnerShip\" WHERE \"listID\" = ?";
       Connection conn = DBConnection.getDBConnection().getConnection();
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setInt(1, id);
       stm.executeUpdate();
   }

   public static void checkItem (ShoppingItems item) throws SQLException, ClassNotFoundException {
       String sql = "UPDATE public.\"ShoppingItems\" SET \"boughtBy\" = ? , \"purchaseDay\" = ? " +
               "WHERE \"itemID\" = ? ";
       Connection conn = DBConnection.getDBConnection().getConnection();
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setInt(3, item.getItemID());
       stm.setDate(2, item.getPurchaseDay());
       stm.setString(1,item.getBoughtBy());
       stm.executeUpdate();
   }

   public static boolean isShared (int id) throws SQLException, ClassNotFoundException {
        String countG = "SELECT COUNT(\"groupID\") FROM public.\"user\" WHERE \"groupID\" = ?";
        String countL = "SELECT COUNT(\"listID\") FROM public.\"OwnerShip\" WHERE \"listID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement countStmt = null;

        countStmt = conn.prepareStatement(countG);
        countStmt.setInt(1,Model.getInstance().getUser().getGroupID());
        ResultSet resultSet = countStmt.executeQuery();
        int resG = 0;
        if (resultSet.next()) {
            resG = resultSet.getInt(1);
        }

       countStmt = conn.prepareStatement(countL);
       countStmt.setInt(1,id);
       resultSet = countStmt.executeQuery();
       int resL = 0;
       if (resultSet.next()) {
           resL = resultSet.getInt(1);
       }
       return resG <= resL;
   }

   public static void shareList (ShoppingList list) throws SQLException, ClassNotFoundException {
        String listUserSql = "SELECT username from public.\"user\" WHERE \"groupID\" = ?";
       Connection conn = DBConnection.getDBConnection().getConnection();
       PreparedStatement listUser = conn.prepareStatement(listUserSql);
       listUser.setInt(1,Model.getInstance().getUser().getGroupID());

       ResultSet resultSet = listUser.executeQuery();
        List<String> listUsername = new ArrayList<>();
       while (resultSet.next()){
           listUsername.add(resultSet.getString(1));
       }
        listUsername.remove(Model.getInstance().getUser().getUsername());
       for (String username : listUsername) {
           addOwner(username, list.getListID(), false);
       }
   }

   public static List<ShoppingItems> getReportItem (int fridgeID) throws SQLException, ClassNotFoundException {
        String sql = "Select DISTINCT \"ShoppingItems\".* FROM public.user, \"ShoppingList\", \"ShoppingItems\"," +
                " \"Fridge\", \"Group\", \"OwnerShip\" " +
                "  WHERE   public.user.username =  \"OwnerShip\".username " +
                "  AND public.user.\"groupID\" = \"Group\".\"groupID\" " +
                "  AND \"OwnerShip\".\"listID\" =   \"ShoppingList\".\"listID\" " +
                "  AND \"ShoppingList\".\"listID\" = \"ShoppingItems\".\"listID\" " +
                "  AND \"Group\".\"groupID\" = \"Fridge\".\"groupID\" " +
                "  AND \"ShoppingItems\".\"purchaseDay\" >= CURRENT_DATE - INTERVAL '7 days' " +
                "  And \"Fridge\".\"fridgeID\" = ?";
       Connection conn = DBConnection.getDBConnection().getConnection();
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setInt(1,fridgeID);

       ResultSet resultSet = stm.executeQuery();
       List<ShoppingItems> items = new ArrayList<>();
       while (resultSet.next()) {
           items.add(new ShoppingItems(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),
                   resultSet.getString(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getString(8),resultSet.getDate(9)));
       }
       return items;
   }

}
