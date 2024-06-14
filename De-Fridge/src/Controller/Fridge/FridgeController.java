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
        fridgeItemBtn.setStyle("-fx-background-color: #38686A");
        fridgeDishBtn.setStyle("-fx-background-color: #578F91");
    }
    private void addListener () {
        fridgeDishBtn.setOnAction(event -> onFridgeDish());
        fridgeItemBtn.setOnAction(event -> onFridgeItems());
    }
    private void onFridgeItems () {
        fridgeItemBtn.setStyle("-fx-background-color: #38686A");
        fridgeDishBtn.setStyle("-fx-background-color: #578F91");
        fridgeView.setCenter(Model.getInstance().getViewFactory().getFridgeItemsView());
    }
    private void onFridgeDish () {
        fridgeItemBtn.setStyle("-fx-background-color: #578F91");
        fridgeDishBtn.setStyle("-fx-background-color: #38686A");
        fridgeView.setCenter(Model.getInstance().getViewFactory().getFridgeDishView());
    }
}
