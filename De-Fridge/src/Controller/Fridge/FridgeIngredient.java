package Controller.Fridge;

//import Model.Ingredient;
import Model.Ingredient;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FridgeIngredient implements Initializable {

    public Label nameLbl;
    public Label quantityLbl;
    public Label categoryLbl;
    public Label expiryDateLbl;

    public Label unitLbl;

   private final Ingredient ingredient;

    public FridgeIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLbl.setText(ingredient.getName());
        quantityLbl.setText(String.valueOf(ingredient.getQuantity()));
        categoryLbl.setText(ingredient.getCategory());
        expiryDateLbl.setText(ingredient.getExpiryDay().toString());
        unitLbl.setText(ingredient.getUnit());
    }

}
