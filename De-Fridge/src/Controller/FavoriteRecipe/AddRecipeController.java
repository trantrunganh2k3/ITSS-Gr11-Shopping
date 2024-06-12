package Controller.FavoriteRecipe;

import Model.Model;
import Model.FavoriteRecipe;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable {

    public AddRecipeController(FavoriteRecipeController controller) {
        this.controller = controller;
    }

    public TextArea descriptionLbl;
    public final FavoriteRecipeController controller;
    public TextArea ingredientLbl;
    public TextField nameLbl;
    public Button saveBtn;
    public Button cancelBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener () {
        cancelBtn.setOnAction(event -> onCancel());
        saveBtn.setOnAction(event -> onSave());
    }

    private void onCancel () {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
    private void onSave () {
        if (nameLbl.getText() == null || ingredientLbl.getText() == null || descriptionLbl.getText() == null) {

        } else {
            FavoriteRecipe recipe = new FavoriteRecipe();
            recipe.setName(nameLbl.getText());
            recipe.setDescription(descriptionLbl.getText());
            recipe.setIngredient(ingredientLbl.getText());
            Model.getInstance().getRecipes().add(recipe);
            controller.displayRecipes();
            onCancel();
        }
    }
}
