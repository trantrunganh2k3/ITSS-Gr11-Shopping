package Controller.FavoriteRecipe;

import Model.Model;
import Model.FavoriteRecipe;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecipeController implements Initializable {

    public Label lblTitle;
    public Button btnDetail;
    public Label lblIngredient;
    public Button cancelBtn;

    private final FavoriteRecipe recipe;

    private final FavoriteRecipeController controller;

    public RecipeController(FavoriteRecipe recipe, FavoriteRecipeController controller) {
        this.recipe = recipe;
        this.controller = controller;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTitle.setText(recipe.getName());
        lblIngredient.setText(recipe.getIngredient());
        addListener();
    }

    private void addListener () {
        btnDetail.setOnAction(event -> {
            try {
                onDetail();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        cancelBtn.setOnAction(event -> {
            try {
                dbController.FavoriteRecipeController.deleteRecipe(this.recipe.getRecipeID());
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            Model.getInstance().setRecipes();
            displayRecipe();
        });
    }
    private void onDetail () throws IOException {
        RecipeDetailController controller = new RecipeDetailController(this.recipe);
        Model.getInstance().getViewFactory().showRecipeDetail(controller);
    }

    public void displayRecipe (){
        controller.displayRecipes();
    }
}
