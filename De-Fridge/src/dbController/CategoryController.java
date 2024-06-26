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

public class CategoryController {

    public static ObservableList<Category> listCate(){
        ObservableList<Category> cateList = FXCollections.observableArrayList();
        String sql = "SELECT id, category FROM public.\"Category\"";
        try{
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet result = stm.executeQuery();

            while (result.next()){
                cateList.add(new Category(result.getInt(1), result.getString(2),
                        CateUnitController.listUnitForCate(new Category(result.getInt(1), result.getString(2)))));
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return cateList;
    }

    public static boolean addCate(Category cate) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO public.\"Category\" (category) VALUES(?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, cate.getCategory());

        return stm.executeUpdate() == 1;
    }

    public static boolean updateCate(Category cate) throws ClassNotFoundException, SQLException{
        CateUnitController.deleteCateUnit(cate);
        for (Unit unit: cate.getUnits()){
            CateUnitController.addCateUnit(cate,unit);
        }
        String sql = "UPDATE public.\"Category\" SET category = ? WHERE id = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setInt(2, cate.getId());
        stm.setString(1, cate.getCategory());

        return stm.executeUpdate() == 1;
    }

    public static boolean deleteCate(Category cate) throws ClassNotFoundException, SQLException{
        CateUnitController.deleteCateUnit(cate);
        String sql = "DELETE FROM public.\"Category\" WHERE id = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, cate.getId());
        return stm.executeUpdate() == 1;
    }

}
