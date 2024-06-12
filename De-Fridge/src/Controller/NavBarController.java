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
    public AnchorPane navGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }
    private void addListener () {
        navFridge.setStyle("-fx-background-color: #C8AB81;" +
                "-fx-background-radius: 15px 15px 0 0;");
        navList.setStyle("-fx-background-color: transparent;");
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: transparent;");
        navFridge.setOnMouseClicked(mouseEvent -> onFridge());
        navList.setOnMouseClicked(mouseEvent -> onShopList());
        navRecipe.setOnMouseClicked(mouseEvent -> onFavRecipe());
        navReport.setOnMouseClicked(mouseEvent -> onReport());
        navGroup.setOnMouseClicked(mouseEvent -> onGroup());
    }

    private void onFridge () {
        navFridge.setStyle("-fx-background-color: #C8AB81;" +
                "-fx-background-radius: 15px 15px 0 0;");
        navList.setStyle("-fx-background-color: transparent;");
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("Fridge");
    }
    private void onShopList () {
        navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: #C8AB81;");
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("ShopList");
    }
    private void onFavRecipe () {
        navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: transparent;");;
        navRecipe.setStyle("-fx-background-color: #C8AB81;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("FavRecipe");
    }
    private void onReport () {
        navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: transparent;");;
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: #C8AB81;" +
                "-fx-background-radius: 0 0 15px 15px;");
        navGroup.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("Report");
    }
    private void onGroup () {
    	navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: transparent;");
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: #C8AB81;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("Group");
    }
}
