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

    public static boolean addUnit(Unit unit) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO public.\"Unit\" (unit) VALUES(?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, unit.getUnit());

        return stm.executeUpdate() == 1;
    }

    public static boolean updateUnit(Unit unit) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"Unit\" SET unit = ? WHERE id = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setInt(2, unit.getId());
        stm.setString(1, unit.getUnit());

        return stm.executeUpdate() == 1;
    }

    public static boolean deleteUnit(Unit unit) throws ClassNotFoundException, SQLException{
        CateUnitController.deleteCateUnit(unit);
        String sql = "DELETE FROM public.\"Unit\" WHERE id = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, unit.getId());
        return stm.executeUpdate() == 1;
    }

}
