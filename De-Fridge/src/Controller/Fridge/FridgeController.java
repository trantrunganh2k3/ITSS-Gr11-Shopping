package Controller.Fridge;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FridgeController implements Initializable {
    public Button fridgeItemBtn;
    public Button fridgeDishBtn;
    public BorderPane fridgeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    private void addListener () {
        fridgeDishBtn.setOnAction(event -> onFridgeDish());
        fridgeItemBtn.setOnAction(event -> onFridgeItems());
    }
    private void onFridgeItems () {
        fridgeView.setCenter(Model.getInstance().getViewFactory().getFridgeItemsView());
    }
    private void onFridgeDish () {
        fridgeView.setCenter(Model.getInstance().getViewFactory().getFridgeDishView());
    }
}
