package Controller.FavoriteRecipe;

import Model.FavoriteRecipe;
import Model.Model;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FavoriteRecipeController implements Initializable {
    public FlowPane recipeContainer;
    public Button btnAddRecipe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayRecipes();
        addListener();
    }

    private void addListener () {
        btnAddRecipe.setOnMouseClicked(mouseEvent -> {
            try {
                onAddProduct();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void onAddProduct () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/favoriteRecipes/recipeAdd.fxml"));
        AddRecipeController controller = new AddRecipeController(this);
        loader.setController(controller);
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public void displayRecipes() {
        recipeContainer.getChildren().clear();
        for (FavoriteRecipe recipe: Model.getInstance().getRecipes()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/favoriteRecipes/recipe.fxml"));
            RecipeController controller = new RecipeController(recipe, this);
            loader.setController(controller);
            AnchorPane anchorPane = null;
            try {
                anchorPane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            recipeContainer.getChildren().add(anchorPane);
        }
    }
}
