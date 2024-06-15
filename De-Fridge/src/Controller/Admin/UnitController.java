package Controller.Admin;

import Model.Model;
import Model.Unit;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UnitController implements Initializable {
    public Button editBtn;
    public Button deleteBtn;
    public Button saveBtn;
    public Button cancelBtn;
    public TextField tfUnitName;

    private final Unit unit;
    private final CateUnitController controller;
    public Label unitLbl;

    public UnitController(Unit unit1, CateUnitController controller) {
        this.unit = unit1;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();
    }

    private void addListener () {
        editBtn.setOnAction(event -> onEdit());
        cancelBtn.setOnAction(event -> init());
        saveBtn.setOnAction(event -> {
            try {
                onSave();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        deleteBtn.setOnAction(event -> {
            try {
                onDelete();
            } catch (IOException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void init() {
        unitLbl.setText(unit.getUnit());
        unitLbl.setVisible(true);
        tfUnitName.setVisible(false);


        deleteBtn.setVisible(true);
        editBtn.setVisible(true);

        saveBtn.setVisible(false);
        cancelBtn.setVisible(false);
    }

    private void onEdit () {
        tfUnitName.setText(unit.getUnit());
        tfUnitName.setVisible(true);
        unitLbl.setVisible(false);


        deleteBtn.setVisible(false);
        editBtn.setVisible(false);

        saveBtn.setVisible(true);
        cancelBtn.setVisible(true);
    }

    private void onSave () throws SQLException, ClassNotFoundException {
        unit.setUnit(tfUnitName.getText());
        dbController.UnitController.updateUnit(unit);
        init();
    }
    private void onDelete () throws IOException, SQLException, ClassNotFoundException {
        dbController.UnitController.deleteUnit(this.unit);
        Model.getInstance().setUnits();
        controller.displayUnit();
    }
}
