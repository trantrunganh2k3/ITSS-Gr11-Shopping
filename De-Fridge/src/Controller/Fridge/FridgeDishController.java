package Controller.Fridge;

import Model.Model;
import Model.Dish;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FridgeDishController implements Initializable {
    public Pane breakfastPane;
    public Pane lunchPane;
    public Pane dinnerPane;
    public HBox breakfastHbox;
    public Button addDishBtn;
    public AddDishController addDishController;
    public DatePicker datePicker;
    public HBox lunchHbox;
    public HBox dinnerHbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initDatePicker();
        displayDish();
        /*for (int i = 0; i< 5; i++)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/dish.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            breakfastHbox.getChildren().add(anchorPane);
        }*/
        addListener();
    }

    private void addListener () {
        addDishBtn.setOnAction(event -> onAddDish());
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            displayDish();
        });
    }

    private void onAddDish () {
        addDishController = new AddDishController(this);
        Model.getInstance().getViewFactory().showAddDishView(addDishController);
    }

    private void initDatePicker () {
        LocalDate now = LocalDate.now();
        datePicker.setValue(now);
    }

    public void displayDish () {
        Model.getInstance().setDishes(Date.valueOf(datePicker.getValue()));
        clear();
        for (Dish dish: Model.getInstance().getDishes()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/fridge/dish.fxml"));
            DishController controller = new DishController(dish, this);
            loader.setController(controller);
            AnchorPane anchorPane = null;
            try {
                anchorPane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (dish.getMeal().equals("Breakfast")) {
                breakfastHbox.getChildren().add(anchorPane);
            } else if (dish.getMeal().equals("Lunch")) {
                lunchHbox.getChildren().add(anchorPane);
            } else {
                dinnerHbox.getChildren().add(anchorPane);
            }

        }
    }
    public void clear () {
        breakfastHbox.getChildren().clear();
        lunchHbox.getChildren().clear();
        dinnerHbox.getChildren().clear();
    }
}
