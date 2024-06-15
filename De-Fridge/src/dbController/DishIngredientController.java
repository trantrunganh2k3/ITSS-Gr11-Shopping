package dbController;

import Model.Dish;
import Model.DishIngredient;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DishIngredientController {

    public static ObservableList<DishIngredient> listDishIngre(int dishID){
        ObservableList<DishIngredient> dishIngreList = FXCollections.observableArrayList();
        String sql = "SELECT \"dishID\", \"ingredientName\", quantity, unit, \"ingredientID\" FROM public.\"Dish\"" +
                "WHERE \"dishID\" = ?";

        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, dishID);
            ResultSet result = stm.executeQuery();

            while (result.next()){
                dishIngreList.add(new DishIngredient(result.getInt(1), result.getString(2), result.getDouble(3), result.getString(4), result.getInt(5)));
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return dishIngreList;
    }

    public static boolean deleteDishIngre(int dishID) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM public.\"DishIngredient\" WHERE \"dishID\" = ?";

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, dishID);

        return stm.executeUpdate() == 1;
    }

    public static boolean updateDishIngre(List<DishIngredient> dishIngreList) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO public.\"DishIngredient\" (\"dishID\", \"ingredientName\", quantity, unit, \"ingredientID\")" +
                "VALUES(?,?,?,?,?)";

        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        for(DishIngredient dishIgre:dishIngreList){
            stm.setInt(1, dishIgre.getDishID());
            stm.setString(2, dishIgre.getIngredientName());
            stm.setDouble(3, dishIgre.getQuantity());
            stm.setString(4, dishIgre.getUnit());
            stm.setInt(5, dishIgre.getIngredientId());

            if(stm.executeUpdate() == 0) return false;
        }

        return true;
    }
}
