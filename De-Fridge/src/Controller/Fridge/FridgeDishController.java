package Controller.Fridge;

import Controller.AddDishController;
import Model.Model;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FridgeDishController implements Initializable {
    public Pane breakfastPane;
    public Pane lunchPane;
    public Pane dinnerPane;
    public HBox breakfastHbox;
    public Button addDishBtn;
    public AddDishController addDishController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i< 5; i++)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/dish.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            breakfastHbox.getChildren().add(anchorPane);
        }
        addListener();
    }

    private void addListener () {
        addDishBtn.setOnAction(event -> onAddDish());
    }

    private void onAddDish () {
        addDishController = new AddDishController();
        Model.getInstance().getViewFactory().showAddDishView(addDishController);
    }
}
