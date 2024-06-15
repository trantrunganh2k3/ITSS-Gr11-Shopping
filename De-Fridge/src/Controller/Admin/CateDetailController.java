package Controller.Admin;

import Controller.Fridge.DishDetailController;
import Controller.Fridge.DishIngredientController;
import Model.Category;
import Model.DishIngredient;
import Model.Model;
import Model.Unit;
import dbController.CategoryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CateDetailController implements Initializable {
    public TextField lblName;
    public Button btnAddUnit;
    public ListView lvUnits;
    public Button btnSave;
    public Button btnCancel;
    public Label lblError;

    public ObservableList<Unit> units;

    private final Category category;

    public final CateController controller;

    public CateDetailController(Category category, CateController controller) {
        this.category = category;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();
    }

    private void addListener () {
        btnSave.setOnAction(event -> {
            try {
                onSave();
            } catch (IOException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        btnCancel.setOnAction(event -> onCancel());
        btnAddUnit.setOnAction(event -> onAddUnit());
    }

    private void init() {
        lblName.setText(category.getCategory());
        units = FXCollections.observableArrayList();
        for (Unit unit:category.getUnits()) {
            units.add(new Unit(unit.getId(),unit.getUnit()));
        }
        lvUnits.setItems(units);
        lblError.setVisible(false);
        lvUnits.setCellFactory(lv -> new ListCell<Unit>() {

            @Override
            protected void updateItem(Unit item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/admin/cateUnitItem.fxml"));
                    CateUnitItemController controller1 = new CateUnitItemController(item,CateDetailController.this);
                    loader.setController(controller1);
                    setText(null);
                    try {
                        setGraphic(loader.load());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void onSave () throws IOException, SQLException, ClassNotFoundException {
        for (Unit unit: units) {
            if (unit.getUnit() == null) {
                lblError.setVisible(true);
                return;
            }
        }
        category.setCategory(lblName.getText());
        category.setUnits(units);
        CategoryController.updateCate(category);
        onCancel();
        controller.controller.displayCate();
    }

    private void onAddUnit () {
        units.add(new Unit());
    }

    private void onCancel () {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
