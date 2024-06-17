package dbController;

import Model.FavoriteRecipe;
import Model.User;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteRecipeController {
    public static void addRecipe(FavoriteRecipe recipe) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO public.\"FavoriteRecipe\" (\"recipeName\", description, ingredient, username)" +
                "VALUES(?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, recipe.getName());
        stm.setString(2, recipe.getDescription());
        stm.setString(3, recipe.getIngredient());
        stm.setString(4, recipe.getUsername());

        stm.executeUpdate();
    }

    public static void updateRecipe(FavoriteRecipe recipe) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE public.\"FavoriteRecipe\" SET \"recipeName\" = ?, description = ?, ingredient = ? " +
                "WHERE \"recipeID\" = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, recipe.getName());
        stm.setString(2, recipe.getDescription());
        stm.setString(3, recipe.getIngredient());
        stm.setInt(4, recipe.getRecipeID());

        stm.executeUpdate();
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

    public static void deleteRecipe(int recipeID) throws ClassNotFoundException, SQLException{
        String sql = "DELETE FROM public.\"FavoriteRecipe\" WHERE \"recipeID\" = ?";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, recipeID);

        stm.executeUpdate();
    }

    public static List<FavoriteRecipe> searchRecipe (String searchInput) {
        String sql = "SELECT \"recipeID\", \"recipeName\", description, ingredient, username FROM public.\"FavoriteRecipe\" WHERE \"recipeName\" ILIKE ?";
        List<FavoriteRecipe> output = new ArrayList<>();
        try {
            Connection conn = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setObject(1, "%" + searchInput + "%");
            ResultSet result = stm.executeQuery();

            while (result.next()){
                output.add(new FavoriteRecipe(result.getInt(1),result.getString(2), result.getString(3),
                        result.getString(4),result.getString(5)));
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return output;
    }
}
