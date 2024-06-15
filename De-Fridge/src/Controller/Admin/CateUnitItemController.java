package Controller.Admin;

import Model.Model;
import Model.Unit;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

import static dbController.UnitController.updateUnit;

public class CateUnitItemController implements Initializable {
    private final Unit unit;

    public final CateDetailController controller;
    public ChoiceBox cbUnit;
    public Button btnDelete;


    public CateUnitItemController(Unit unit, CateDetailController controller) {
        this.unit = unit;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();
    }

    private void init() {
        for (Unit unit1: Model.getInstance().getUnits()) {
            cbUnit.getItems().add(unit1.getUnit());
        }
        cbUnit.setValue(unit.getUnit());
    }

    private void addListener () {
        cbUnit.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (Unit unit1: Model.getInstance().getUnits()) {
                if (unit1.getUnit().equals(newValue)) {
                    unit.setUnit(unit1.getUnit());
                    unit.setId(unit1.getId());
                }
            }
            unit.setUnit(newValue.toString());
        });
        btnDelete.setOnAction(event -> onDelete());
    }

    private void onDelete () {
        controller.units.remove(unit);
    }
}
