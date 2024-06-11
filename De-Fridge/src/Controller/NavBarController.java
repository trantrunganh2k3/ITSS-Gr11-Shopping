package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NavBarController implements Initializable {
    public AnchorPane navFridge;
    public AnchorPane navList;
    public AnchorPane navRecipe;
    public AnchorPane navReport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    private void addListener () {
        navFridge.setOnMouseClicked(mouseEvent -> onFridge());
        navList.setOnMouseClicked(mouseEvent -> onShopList());
        navRecipe.setOnMouseClicked(mouseEvent -> onFavRecipe());
        navReport.setOnMouseClicked(mouseEvent -> onReport());
    }

    private void onFridge () {
        Model.getInstance().getViewFactory().getHomeSelectView().set("Fridge");
    }
    private void onShopList () {
        Model.getInstance().getViewFactory().getHomeSelectView().set("ShopList");
    }
    private void onFavRecipe () {
        Model.getInstance().getViewFactory().getHomeSelectView().set("FavRecipe");
    }
    private void onReport () {
        Model.getInstance().getViewFactory().getHomeSelectView().set("Report");
    }
}
