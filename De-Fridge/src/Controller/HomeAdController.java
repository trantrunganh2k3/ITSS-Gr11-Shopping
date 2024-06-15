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
                case "AccInfo" -> homeAdView.setCenter(Model.getInstance().getViewFactory().getAccInfoView());
                case "CateList" -> homeAdView.setCenter(Model.getInstance().getViewFactory().getCateUnitView());
                default -> homeAdView.setCenter(Model.getInstance().getViewFactory().getListUserView());
            }
        });
    }
}
