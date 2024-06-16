package Controller.Report;

import Model.Model;
import Model.Dish;
import Model.ShoppingItems;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    public Button btnDish;
    public Button btnIngredient;
    public AnchorPane apIngredient;
    public VBox vbIngredient;
    public AnchorPane apDish;
    public VBox vbDish;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
        try {
            onIngredient();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void addListener () {
        btnIngredient.setOnAction(event -> {
            try {
                onIngredient();
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnDish.setOnAction(event -> {
            try {
                onDish();
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void onIngredient () throws SQLException, ClassNotFoundException, IOException {
        apDish.setVisible(false);
        apIngredient.setVisible(true);

        btnIngredient.setStyle("-fx-background-color: #38686A");
        btnDish.setStyle("-fx-background-color: #578F91");

        vbIngredient.getChildren().clear();
        for (ShoppingItems item: Model.getInstance().getReportShoppingItems()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/report/reportIngredient.fxml"));
            ReportIngredientController controller = new ReportIngredientController(item);
            loader.setController(controller);
            AnchorPane anchorPane = loader.load();
            vbIngredient.getChildren().add(anchorPane);
        }
    }

    private void onDish () throws SQLException, ClassNotFoundException, IOException {
        apDish.setVisible(true);
        apIngredient.setVisible(false);

        btnDish.setStyle("-fx-background-color: #38686A");
        btnIngredient.setStyle("-fx-background-color: #578F91");

        vbDish.getChildren().clear();
        for (Dish dish: Model.getInstance().getReportDish()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/report/reportDish.fxml"));
            ReportDishController controller = new ReportDishController(dish);
            loader.setController(controller);
            AnchorPane anchorPane = loader.load();
            vbDish.getChildren().add(anchorPane);
        }
    }
}
