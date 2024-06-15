package Controller.Admin;

import Model.Category;
import Model.Model;
import dbController.CategoryController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CateController implements Initializable {
    public Label cateLbl;
    public Button editBtn;
    public Button deleteBtn;
    private final Category category;
    public final CateUnitController controller;

    public CateController(Category category, CateUnitController controller) {
        this.category = category;
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        addListener();
    }

    private void init() {
        cateLbl.setText(category.getCategory());
    }

    private void addListener () {
        deleteBtn.setOnAction(event -> {
            try {
                onDelete();
            } catch (IOException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        editBtn.setOnAction(event -> onEdit());
    }

    private void onEdit (){
        CateDetailController controller1 = new CateDetailController(category,this);
        Model.getInstance().getViewFactory().showCateDetail(controller1);
    }

    private void onDelete () throws IOException, SQLException, ClassNotFoundException {
        CategoryController.deleteCate(category);
        Model.getInstance().setCategories();
        controller.displayCate();
    }
}
