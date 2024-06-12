package dbController;

import Model.Dish;
import Model.User;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FridgeController {

    public static ObservableList<Dish> listDish(){
        ObservableList<Dish> dishList = FXCollections.observableArrayList();
        String sql = "SELECT dishID, name, meal, username, fridgeID FROM public.\"Dish\"";

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
}
