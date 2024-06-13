package dbController;

import Model.Category;
import Model.Unit;
import Model.User;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitController {

    public static ObservableList<Unit> listUnit(){
        ObservableList<Unit> unitList = FXCollections.observableArrayList();
        String sql = "SELECT id, unit FROM public.\"Unit\"";
        try{
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet result = stm.executeQuery();

            while (result.next()){
                unitList.add(new Unit(result.getInt(1), result.getString(2)));
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return unitList;
    }

    public static ObservableList<Unit> listUnitForCate(Category cate){
        ObservableList<Unit> unitList = FXCollections.observableArrayList();
        String sql = "SELECT id, unit FROM public.\"Unit\" JOIN public.\"uOWNc\" " +
                "WHERE public.\"Unit\".id = public.\"uOWNc\".\"unitID\" AND public.\"uOWNc\".\"CateID\" = ?";
        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, cate.getId());
            ResultSet result = stm.executeQuery();

            while (result.next()){
                unitList.add(new Unit(result.getInt(1), result.getString(2)));
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return  unitList;
    }
}
