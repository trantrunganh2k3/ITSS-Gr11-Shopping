package Controller;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NavBarController implements Initializable {
    public AnchorPane navFridge;
    public AnchorPane navList;
    public AnchorPane navRecipe;
    public AnchorPane navReport;
    public AnchorPane navGroup;
    public AnchorPane navAccInfo;
    public AnchorPane navLogout;


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
        navGroup.setStyle("-fx-background-color: transparent;");
        navAccInfo.setStyle("-fx-background-color: transparent;");
        navFridge.setOnMouseClicked(mouseEvent -> onFridge());
        navList.setOnMouseClicked(mouseEvent -> onShopList());
        navRecipe.setOnMouseClicked(mouseEvent -> onFavRecipe());
        navReport.setOnMouseClicked(mouseEvent -> onReport());
        navGroup.setOnMouseClicked(mouseEvent -> onGroup());
        navAccInfo.setOnMouseClicked(mouseEvent -> onAccInfo());
        navLogout.setOnMouseClicked(mouseEvent -> onLogout());
    }

    private void onFridge () {
        navFridge.setStyle("-fx-background-color: #C8AB81;" +
                "-fx-background-radius: 15px 15px 0 0;");
        navList.setStyle("-fx-background-color: transparent;");
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: transparent;");
        navAccInfo.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("Fridge");
    }
    private void onShopList () {
        navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: #C8AB81;");
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: transparent;");
        navAccInfo.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("ShopList");
    }
    private void onFavRecipe () {
        navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: transparent;");;
        navRecipe.setStyle("-fx-background-color: #C8AB81;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: transparent;");
        navAccInfo.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("FavRecipe");
    }
    private void onReport () {
        navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: transparent;");;
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: #C8AB81;" +
                "-fx-background-radius: 0 0 15px 15px;");
        navGroup.setStyle("-fx-background-color: transparent;");
        navAccInfo.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("Report");
    }
    private void onGroup () {
    	navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: transparent;");
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: #C8AB81;" +
                "-fx-background-radius: 15px 15px 0 0;");
        navAccInfo.setStyle("-fx-background-color: transparent;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("Group");
    }

    private void onAccInfo(){
        navFridge.setStyle("-fx-background-color: transparent;");
        navList.setStyle("-fx-background-color: transparent;");;
        navRecipe.setStyle("-fx-background-color: transparent;");
        navReport.setStyle("-fx-background-color: transparent;");
        navGroup.setStyle("-fx-background-color: transparent;");
        navAccInfo.setStyle("-fx-background-color: #C8AB81;");
        Model.getInstance().getViewFactory().getHomeSelectView().set("AccInfo");
    }

    private void onLogout(){
        Model.getInstance().deleteAll();
        Stage stage = (Stage) navLogout.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginSignup();
    }
}
