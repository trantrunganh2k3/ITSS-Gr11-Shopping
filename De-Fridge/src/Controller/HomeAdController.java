package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeAdController implements Initializable {
    public BorderPane homeAdView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getHomeSelectView().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "ShopList" -> homeAdView.setCenter(Model.getInstance().getViewFactory().getShopListView());
                case "FavRecipe"-> homeAdView.setCenter(Model.getInstance().getViewFactory().getFavoriteRecipeView());
                case "Report" -> homeAdView.setCenter(Model.getInstance().getViewFactory().getReportView());
                case "Group" -> homeAdView.setCenter(Model.getInstance().getViewFactory().getGroupView());
                default -> homeAdView.setCenter(Model.getInstance().getViewFactory().getFridgeView());
            }
        });
    }
}
