package dbController;

import Model.Category;
import Model.Unit;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CateUnitController {
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

    public static boolean deleteCateUnit(Category cate) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM public.\"uOWNc\" WHERE \"cateID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, cate.getId());

        return stm.executeUpdate() == 1;
    }

    public static boolean deleteCateUnit(Unit unit) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM public.\"uOWNc\" WHERE \"unitID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, unit.getId());

        return stm.executeUpdate() == 1;
    }

    public static boolean addCateUnit(Category cate, Unit unit) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO public.\"uOWNc\" (\"cateID\", \"unitID\") VALUES(?, ?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, cate.getId());
        stm.setInt(2, unit.getId());

        return stm.executeUpdate() == 1;
    }
}
