package dbController;

import Model.Dish;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DishController {

    public static ObservableList<Dish> listDish(){
        ObservableList<Dish> dishList = FXCollections.observableArrayList();
        String sql = "SELECT \"dishID\", name, meal, username, \"fridgeID\" FROM public.\"Dish\"";

        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet result = stm.executeQuery();

            while (result.next()){
                dishList.add(new Dish(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5)));
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return dishList;
    }

    public static int countDish() throws ClassNotFoundException, SQLException{
        String sql = "SELECT COUNT(\"dishID\") FROM public.\"Dish\"";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet result = stm.executeQuery();

        int res = 0;
        while (result.next()){
            res = result.getInt(1);
        }
        return res;
    }

    public static boolean addDish(Dish dish) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO public.\"Dish\" (\"dishID\", name, meal, username, \"fridgeID\") " +
                "VALUES(?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, dish.getDishID());
        stm.setString(2, dish.getName());
        stm.setString(3, dish.getMeal());
        stm.setString(4, dish.getUsername());
        stm.setInt(5, dish.getFridgeID());

        return stm.executeUpdate() == 1;
    }

    public static boolean updateDish(Dish dish) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"Dish\" SET name = ?, meal = ? WHERE \"dishID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, dish.getName());
        stm.setString(2, dish.getMeal());
        stm.setInt(3, dish.getDishID());

        return stm.executeUpdate() == 1;
    }

    public static boolean deleteDish(int dishID) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM public.\"Dish\" WHERE \"dishID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, dishID);

        return stm.executeUpdate() == 1;
    }

}
