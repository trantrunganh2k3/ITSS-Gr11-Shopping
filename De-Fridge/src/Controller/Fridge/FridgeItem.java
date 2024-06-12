package Controller.Fridge;

import Model.Model;
import Model.Ingredient;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FridgeItem implements Initializable {
    public VBox itemsVbox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            test();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void test () throws IOException {

        for (Ingredient ingredient: Model.getInstance().getIngredients()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/fridgeIngredient.fxml"));
            FridgeIngredient controller = new FridgeIngredient(ingredient);
            loader.setController(controller);
            itemsVbox.getChildren().add(loader.load());
        }

       /* Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Do an");
        ingredient1.setCategory("Food");
        ingredient1.setQuantity(500);
        ingredient1.setExpiryDay("2024-06-15");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/fridgeIngredient.fxml"));
        FridgeIngredient controller = new FridgeIngredient(ingredient1);
        loader.setController(controller);
        itemsVbox.getChildren().add(loader.load());*/
    }

}
