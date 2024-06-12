package Controller.FavoriteRecipe;

import Model.FavoriteRecipe;
import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeDetailController implements Initializable {
    public TextArea descriptionLbl;
    public TextArea ingredientLbl;
    public TextField nameLbl;
    public Button saveBtn;
    public Button editBtn;
    public Button cancelBtn;

    public final FavoriteRecipe recipe;

    public RecipeDetailController(FavoriteRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();
    }

    private void addListener () {
        cancelBtn.setOnAction(event -> init());
        saveBtn.setOnAction(event -> save());
        editBtn.setOnAction(event -> edit());
    }
    private void init() {
        nameLbl.setText(recipe.getName());
        ingredientLbl.setText(recipe.getIngredient());
        descriptionLbl.setText(recipe.getDescription());
        editBtn.setVisible(true);
        saveBtn.setVisible(false);
        cancelBtn.setVisible(false);
        nameLbl.setEditable(false);
        ingredientLbl.setEditable(false);
        descriptionLbl.setEditable(false);
    }

    private void edit() {
        editBtn.setVisible(false);
        saveBtn.setVisible(true);
        cancelBtn.setVisible(true);
        nameLbl.setEditable(true);
        ingredientLbl.setEditable(true);
        descriptionLbl.setEditable(true);
    }

    private void save () {
        recipe.setName(nameLbl.getText());
        recipe.setIngredient(ingredientLbl.getText());
        recipe.setDescription(descriptionLbl.getText());
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
