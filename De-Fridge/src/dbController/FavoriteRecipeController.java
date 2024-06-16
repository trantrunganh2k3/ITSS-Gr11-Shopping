package dbController;

import Model.FavoriteRecipe;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FavoriteRecipeController {
    public static boolean addRecipe(FavoriteRecipe recipe) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO public.\"FavoriteRecipe\" (\"recipeName\", description, ingredient, username)" +
                "VALUES(?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, recipe.getName());
        stm.setString(2, recipe.getDescription());
        stm.setString(3, recipe.getIngredient());
        stm.setString(4, recipe.getUsername());

        return stm.executeUpdate() == 1;
    }

    public static boolean updateRecipe(FavoriteRecipe recipe) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE public.\"FavoriteRecipe\" SET \"recipeName\" = ?, description = ?, ingredient = ? " +
                "WHERE \"recipeID\" = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, recipe.getName());
        stm.setString(2, recipe.getDescription());
        stm.setString(3, recipe.getIngredient());
        stm.setInt(4, recipe.getRecipeID());

        return stm.executeUpdate() == 1;
    }

    public static ObservableList<FavoriteRecipe> listRecipe(String username){
        ObservableList<FavoriteRecipe> recipeList = FXCollections.observableArrayList();
        String sql = "SELECT \"recipeID\", \"recipeName\", description, ingredient, username FROM public.\"FavoriteRecipe\"" +
                "WHERE username = ?";

        try{
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet result = stm.executeQuery();

            while (result.next()){
                recipeList.add(new FavoriteRecipe(result.getInt(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5)));
            }
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return recipeList;
    }

    public static boolean deleteRecipe(int recipeID) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM public.\"FavoriteRecipe\" WHERE \"recipeID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, recipeID);

        return stm.executeUpdate() == 1;
    }
}
