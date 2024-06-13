package dbController;

import Model.Ingredient;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class IngredientController {

    public static ObservableList<Ingredient> listIngre(){
        ObservableList<Ingredient> ingreList = FXCollections.observableArrayList();
        String sql = "SELECT \"ingredientID\", name, category, quantity, unit," +
                "\"purchaseDay\", \"expiryDay\", \"fridgeID\" FROM public.\"Ingredient\"" +
                "WHERE \"purchaserDay\" < ? AND quantity > 0";

        try{
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, String.valueOf(LocalDateTime.now()));
            ResultSet result = stm.executeQuery();

            while (result.next()){
                ingreList.add(new Ingredient(result.getInt(1), result.getString(2), result.getString(3),
                        result.getInt(4), result.getString(5), result.getDate(6), result.getDate(7), result.getInt(8)));
            }
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return ingreList;
    }

    public static boolean updateQuan(int quan, int ingredientID) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"Ingredient\" SET quantity = ? WHERE \"ingredientID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, quan);
        stm.setInt(2, ingredientID);

        return stm.executeUpdate() == 1;
    }
}
