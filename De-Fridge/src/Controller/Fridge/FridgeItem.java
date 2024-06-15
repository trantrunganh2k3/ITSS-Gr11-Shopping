package Controller.Fridge;

import Model.Model;
import Model.Ingredient;
import javafx.collections.ListChangeListener;
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
            displayIngredient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Model.getInstance().getIngredients().addListener((ListChangeListener.Change<? extends Ingredient> c) -> {
                    try {
                        displayIngredient();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    private void displayIngredient() throws IOException {
        itemsVbox.getChildren().clear();
        for (Ingredient ingredient: Model.getInstance().getIngredients()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/fridgeIngredient.fxml"));
            FridgeIngredient controller = new FridgeIngredient(ingredient);
            loader.setController(controller);
            itemsVbox.getChildren().add(loader.load());
        }

    }

}
