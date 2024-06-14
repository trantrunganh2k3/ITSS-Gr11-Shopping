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

public class DishIngredientController {

    public static ObservableList<DishIngredient> listDishIngre(int dishID){
        ObservableList<DishIngredient> dishIngreList = FXCollections.observableArrayList();
        String sql = "SELECT \"dishID\", name, quantity, unit, \"ingredientID\" FROM public.\"Dish\"" +
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


}
