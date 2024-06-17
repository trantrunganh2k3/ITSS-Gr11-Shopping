package Controller.FavoriteRecipe;

import Model.FavoriteRecipe;
import Model.Model;
import dbController.FavoriteRecipeController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RecipeDetailController implements Initializable {
    public TextArea descriptionLbl;
    public TextArea ingredientLbl;
    public TextField nameLbl;
    public Button saveBtn;
    public Button editBtn;
    public Button cancelBtn;

    public final FavoriteRecipe recipe;
    private final RecipeController controller;
    public Button closeBtn;

    public RecipeDetailController(FavoriteRecipe recipe, RecipeController controller) {
        this.recipe = recipe;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();
    }

    private void addListener () {
        cancelBtn.setOnAction(event -> init());
        saveBtn.setOnAction(event -> {
            try {
                save();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        editBtn.setOnAction(event -> edit());
        closeBtn.setOnAction(event -> close());
    }
    private void init() {
        nameLbl.setText(recipe.getName());
        ingredientLbl.setText(recipe.getIngredient());
        descriptionLbl.setText(recipe.getDescription());
        editBtn.setVisible(true);
        saveBtn.setVisible(false);
        cancelBtn.setVisible(false);
        closeBtn.setVisible(true);
        nameLbl.setEditable(false);
        ingredientLbl.setEditable(false);
        descriptionLbl.setEditable(false);
    }

    private void edit() {
        editBtn.setVisible(false);
        saveBtn.setVisible(true);
        cancelBtn.setVisible(true);
        closeBtn.setVisible(false);
        nameLbl.setEditable(true);
        ingredientLbl.setEditable(true);
        descriptionLbl.setEditable(true);
    }

    private void save () throws SQLException, ClassNotFoundException {
        recipe.setName(nameLbl.getText());
        recipe.setIngredient(ingredientLbl.getText());
        recipe.setDescription(descriptionLbl.getText());
        FavoriteRecipeController.updateRecipe(recipe);
        Model.getInstance().setRecipes();
        close();
        controller.controller.returnHome();
    }

    private void close () {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
