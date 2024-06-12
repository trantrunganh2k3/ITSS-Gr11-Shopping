package Controller.Group;

//import Model.Ingredient;
import Model.Ingredient;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupIngredient implements Initializable {

    public Label nameLbl;
    public Label quantityLbl;
    public Label categoryLbl;
    public Label expiryDateLbl;

    public Label unitLbl;

   private final Ingredient ingredient;

    public GroupIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLbl.setText(ingredient.getName());
        quantityLbl.setText(String.valueOf(ingredient.getQuantity()));
        categoryLbl.setText(ingredient.getCategory());
        expiryDateLbl.setText(ingredient.getExpiryDay());
        unitLbl.setText(ingredient.getUnit());
    }

}
