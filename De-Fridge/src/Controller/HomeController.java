package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public BorderPane homeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getHomeSelectView().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "ShopList" -> homeView.setCenter(Model.getInstance().getViewFactory().getShopListView());
                case "FavRecipe"-> homeView.setCenter(Model.getInstance().getViewFactory().getFavoriteRecipeView());
                case "Report" -> homeView.setCenter(Model.getInstance().getViewFactory().getReportView());
                case "Group" -> homeView.setCenter(Model.getInstance().getViewFactory().getGroupView());
                case "AccInfo" -> homeView.setCenter(Model.getInstance().getViewFactory().getAccInfoView());
                /*case "Logout" -> {
                    Stage stage = (Stage)Model.get
                    //Model.getInstance().getViewFactory().closeStage(stage);
                    Model.getInstance().getViewFactory().showLoginSignup();
                }*/
                default -> homeView.setCenter(Model.getInstance().getViewFactory().getFridgeView());
            }
        });
    }
}
