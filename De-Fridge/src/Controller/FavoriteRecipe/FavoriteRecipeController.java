package Controller.FavoriteRecipe;

import Model.FavoriteRecipe;
import Model.Model;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.MissingFormatArgumentException;
import java.util.ResourceBundle;

public class FavoriteRecipeController implements Initializable {
    public FlowPane recipeContainer;
    public Button btnAddRecipe;
    public Button searchBtn;
    public TextField searchTf;
    public Button unSearchBtn;
    public Label errorLbl;

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
        searchBtn.setOnAction(event -> searchRecipe());
        unSearchBtn.setOnAction(event -> {
            searchTf.clear();
            displayRecipes();
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
        unSearchBtn.setVisible(false);
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

    private void searchRecipe () {
        if (searchTf.getText().isEmpty()) {

        } else {
            unSearchBtn.setVisible(true);
            recipeContainer.getChildren().clear();
            Model.getInstance().setSearchRecipes(searchTf.getText());
            if (Model.getInstance().getSearchRecipes().isEmpty()) {
                errorLbl.setText("No results found for '" + searchTf.getText() +
                        "'. Please try a different keyword or refine your search.");
                recipeContainer.getChildren().add(errorLbl);
            } else {
                for (FavoriteRecipe recipe: Model.getInstance().getSearchRecipes()){
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
    }
    public void returnHome () {
        if (searchTf.getText().isEmpty()) {
            displayRecipes();
        } else {
            searchRecipe();
        }
    }
}
