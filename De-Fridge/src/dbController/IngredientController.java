package dbController;

import Model.DishIngredient;
import Model.Ingredient;
import Model.Dish;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class IngredientController {

    public static ObservableList<Ingredient> listIngre(int fridgeID){
        ObservableList<Ingredient> ingreList = FXCollections.observableArrayList();
        String sql = "SELECT \"ingredientID\", name, category, quantity, unit," +
                "\"purchaseDay\", \"expiryDay\", \"fridgeID\" FROM public.\"Ingredient\" " +
                "WHERE \"expiryDay\" > ? AND quantity > 0 AND \"fridgeID\" = ? ";

        try{
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setObject(1, Date.valueOf(LocalDate.now()));
            stm.setInt(2, fridgeID);
            ResultSet result = stm.executeQuery();

            while (result.next()){
                ingreList.add(new Ingredient(result.getInt(1), result.getString(2), result.getString(3),
                        result.getDouble(4), result.getString(5), result.getDate(6), result.getDate(7), result.getInt(8)));
            }
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return ingreList;
    }

    public static void updateQuan(Double quan, int ingredientID, String opp) throws ClassNotFoundException, SQLException{
        String sql ;
        if (opp.equals("+")) {
            sql = "UPDATE public.\"Ingredient\" SET quantity = (quantity + ?) WHERE \"ingredientID\" = ?";
        } else {
            sql = "UPDATE public.\"Ingredient\" SET quantity = (quantity - ?) WHERE \"ingredientID\" = ?";
        }
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setDouble(1, quan);
        stm.setInt(2, ingredientID);
        stm.executeUpdate();

    }

    public static void addIngredient (int fridgeId, Ingredient ingredient) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO public.\"Ingredient\" (name, category, quantity, unit, \"purchaseDay\", \"expiryDay\",\"fridgeID\")" +
                " VALUES(?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, ingredient.getName());
        stm.setString(2, ingredient.getCategory());
        stm.setDouble(3,ingredient.getQuantity());
        stm.setString(4,ingredient.getUnit());
        stm.setDate(5,ingredient.getPurchaseDay());
        stm.setDate(6,ingredient.getExpiryDay());
        stm.setInt(7, fridgeId);

        stm.executeUpdate();
    }

//    public static void minusIngredient (Dish dish) {
//        for (DishIngredient di)
//    }
}
