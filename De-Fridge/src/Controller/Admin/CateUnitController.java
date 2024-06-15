package Controller.Admin;

import Model.Category;
import Model.Unit;
import Model.Model;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CateUnitController implements Initializable {
    public VBox cateVbox;
    public VBox unitVbox;
    public Button addCateBtn;
    public Button addUnitBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayCate();
            displayUnit();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addListener();

    }

    private void addListener () {
        addUnitBtn.setOnAction(event -> {
            try {
                onAddUnit();
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void displayCate () throws IOException {
        for (Category category: Model.getInstance().getCategories()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/admin/cate.fxml"));
            //UserItemController controller = new UserItemController(user);
            //loader.setController(controller);
            cateVbox.getChildren().add(loader.load());
        }
    }

    public void displayUnit () throws IOException {
        unitVbox.getChildren().clear();
        for (Unit unit: Model.getInstance().getUnits()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/admin/unit.fxml"));
            Controller.Admin.UnitController controller = new UnitController(unit,this);
            loader.setController(controller);
            unitVbox.getChildren().add(loader.load());
        }
    }

    public void onAddUnit() throws SQLException, ClassNotFoundException, IOException {
        Unit newUnit = new Unit();
        newUnit.setUnit("New");
        dbController.UnitController.addUnit(newUnit);
        Model.getInstance().setUnits();
        displayUnit();
    }
}
