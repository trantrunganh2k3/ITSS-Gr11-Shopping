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
        for (Category unit: Model.getInstance().getCategories()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/fxml/admin/unit.fxml"));
            //UserItemController controller = new UserItemController(user);
            //loader.setController(controller);
            unitVbox.getChildren().add(loader.load());
        }
    }
}
