package Controller.Report;

import Model.Dish;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportDishController implements Initializable {
    public Label lblName;
    public Label lblDate;
    public Label lblSection;
    public Label lblMakeBy;
    private final Dish dish;

    public ReportDishController(Dish dish) {
        this.dish = dish;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblName.setText(dish.getName());
        lblDate.setText(dish.getForDate().toString());
        lblSection.setText(dish.getMeal());
        lblMakeBy.setText(dish.getUsername());
    }
}
