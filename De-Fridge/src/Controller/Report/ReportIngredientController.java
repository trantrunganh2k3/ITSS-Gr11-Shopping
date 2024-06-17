package Controller.Report;

import Model.ShoppingItems;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportIngredientController implements Initializable {
    public Label lblName;
    public Label lblCate;
    public Label lblQuantity;
    public Label lblUnit;
    public Label lblBoughtBy;
    public Label lblDay;

    private final ShoppingItems item;

    public ReportIngredientController(ShoppingItems item) {
        this.item = item;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblName.setText(item.getItemName());
        lblCate.setText(item.getCategory());
        lblQuantity.setText(String.valueOf(item.getQuantity()));
        lblUnit.setText(item.getUnit());
        lblBoughtBy.setText(item.getBoughtBy());
        lblDay.setText(item.getPurchaseDay().toString());
    }
}
