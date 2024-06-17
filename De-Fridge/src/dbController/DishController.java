package dbController;

import Model.Dish;
import Model.DishIngredient;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishController {

    public static ObservableList<Dish> listDish(int fridgeID, Date date){
        ObservableList<Dish> dishList = FXCollections.observableArrayList();
        String sql = "SELECT \"dishID\", name, meal, username, \"fridgeID\" FROM public.\"Dish\" WHERE \"fridgeID\" = ? AND" +
                "\"forDate\" = ?";
        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,fridgeID);
            stm.setDate(2,date);
            ResultSet result = stm.executeQuery();

            while (result.next()){
                Dish dish = new Dish(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5));
                dish.setIngredients(DishIngredientController.listDishIngre(dish.getDishID()));
                dishList.add(dish);

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

    public static void addDish(Dish dish) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO public.\"Dish\" (\"dishID\", name, meal, username, \"forDate\", \"fridgeID\") " +
                "VALUES(?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        dish.setDishID(countDish()+1);
        stm.setInt(1, dish.getDishID());
        stm.setString(2, dish.getName());
        stm.setString(3, dish.getMeal());
        stm.setString(4, dish.getUsername());
        stm.setDate(5,dish.getForDate());
        stm.setInt(6, dish.getFridgeID());
        stm.executeUpdate();
        DishIngredientController.updateDishIngre(dish.getIngredients(), dish.getDishID());

    }

    public static void updateDish(Dish dish) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE public.\"Dish\" SET name = ?, meal = ? WHERE \"dishID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, dish.getName());
        stm.setString(2, dish.getMeal());
        stm.setInt(3, dish.getDishID());
        stm.executeUpdate();
        DishIngredientController.deleteDishIngre(dish.getDishID());
        DishIngredientController.updateDishIngre(dish.getIngredients(),dish.getDishID());
    }

    public static boolean deleteDish(int dishID) throws ClassNotFoundException, SQLException{
        DishIngredientController.deleteDishIngre(dishID);
        String sql = "DELETE FROM public.\"Dish\" WHERE \"dishID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, dishID);
        return stm.executeUpdate() == 1;
    }

    public static List<Dish> getReportDish (int fridgeID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM \"Dish\" WHERE \"fridgeID\" = ? " +
                "AND \"forDate\" >= CURRENT_DATE - INTERVAL '7 days' ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1,fridgeID);
        ResultSet resultSet = stm.executeQuery();
        List<Dish> dishes = new ArrayList<>();
        while (resultSet.next()) {
            dishes.add(new Dish(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                    resultSet.getDate(4),resultSet.getString(5), fridgeID));
        }
        return dishes;
    }

}
