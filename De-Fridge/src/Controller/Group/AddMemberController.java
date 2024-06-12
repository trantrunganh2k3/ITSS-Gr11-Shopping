package Controller.Group;

import Model.Dish;
import Model.DishIngredient;
import Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.security.interfaces.DSAKey;
import java.util.ResourceBundle;

public class AddMemberController implements Initializable {
    public TextField nameMemberLbl;
    public Button addIngredientBtn;
    public ListView ingredientLV;
    public ChoiceBox sectionChoiceBox;
    public Button saveBtn;
    public Button cancelBtn;
    private final GroupMemberController controller;
    public AddMemberController(GroupMemberController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sectionChoiceBox.getItems().addAll("Member","Leader");
        addListener();
    }

    private void addListener () {
        /* saveBtn.setOnAction(event -> onSubmit());*/ 
        cancelBtn.setOnAction(event -> closeStage());
    }

    /* private void onSubmit (){
        if (nameMemberLbl.getText() == null || sectionChoiceBox.getValue() ==null) {

        } else {
            for (DishIngredient ingredient: this.ingredients) {
                if (ingredient.getIngredientName() == null ||
                ingredient.getQuantity() == 0  ) {
                    return;
                }
            }
            controller.displayDish();
            closeStage();
        }
    } */
    private void closeStage () {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
