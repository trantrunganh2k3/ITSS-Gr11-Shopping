package View;

import Controller.AddDishController;
import Controller.DishDetailController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewFactory {

    private final StringProperty loginSignupSelect;
    private VBox loginView;
    private VBox signupView;
    private BorderPane fridgeView;
    private BorderPane shopListView;
    private AnchorPane favoriteRecipeView;
    private AnchorPane reportView;
    private AnchorPane fridgeItemsView;
    private AnchorPane fridgeDishView;
    private AnchorPane dishDetailView;
    private FXMLLoader fridgeItem;

    private final StringProperty homeSelectView;


    public ViewFactory () {
        this.homeSelectView = new SimpleStringProperty("");
        this.loginSignupSelect = new SimpleStringProperty("");
    }

    public StringProperty getLoginSignupSelect() {
        return this.loginSignupSelect;
    }
    public StringProperty getHomeSelectView() { return this.homeSelectView;}


    public VBox getLoginView() {
        if (loginView == null) {
            try {
                loginView = new FXMLLoader(getClass().getResource("fxml/login.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return loginView;
    }

    public AnchorPane  getFridgeItemsView() {
        if (fridgeItemsView == null) {
            try {
                fridgeItemsView = new FXMLLoader(getClass().getResource("fxml/fridge/fridgeItem.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fridgeItemsView;
    }

    public AnchorPane  getFridgeDishView() {
        if (fridgeDishView == null) {
            try {
                fridgeDishView = new FXMLLoader(getClass().getResource("fxml/fridge/fridgeDish.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fridgeDishView;
    }
    public AnchorPane getDishDetailView() {
        if (dishDetailView == null) {
            try {
                dishDetailView = new FXMLLoader(getClass().getResource("fxml/fridge/dishDetail.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dishDetailView;
    }

    public FXMLLoader getFridgeItem() {
        if (fridgeItem == null) {
            try {
                fridgeItem = new FXMLLoader(getClass().getResource("fxml/fridge/fridgeIngredient.fxml"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fridgeItem;
    }


    public void showAddDishView(AddDishController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/fridge/addDish.fxml"));
        loader.setController(controller);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("De-fridge");
        stage.show();
    }

    public void showDishDetailView(DishDetailController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/fridge/dishDetail.fxml"));
        loader.setController(controller);
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("De-fridge");
        stage.show();
    }

    public VBox getSignupView() {
        if (signupView == null) {
            try {
                signupView = new FXMLLoader(getClass().getResource("fxml/signup.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return signupView;
    }

    public BorderPane getShopListView () {
        if (shopListView == null) {
            try {
                shopListView = new FXMLLoader(getClass().getResource("fxml/shoppingList/shoppingList.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.shopListView;
    }
    public BorderPane getFridgeView () {
        if (fridgeView == null) {
            try {
                fridgeView = new FXMLLoader(getClass().getResource("fxml/fridge/fridge.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.fridgeView;
    }
    public AnchorPane getFavoriteRecipeView () {
        if (favoriteRecipeView == null) {
            try {
                favoriteRecipeView = new FXMLLoader(getClass().getResource("fxml/favoriteRecipes/favoriteRecipe.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.favoriteRecipeView;
    }
    public AnchorPane getReportView () {
        if (reportView == null) {
            try {
                reportView = new FXMLLoader(getClass().getResource("fxml/report/report.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.reportView;
    }




    public void showHome() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/home.fxml"));
        createStage(loader);
    }
    public void showLoginSignup () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/loginSignup.fxml"));
        createStage(loader);
    }
    /*
    /
     */
    private void createStage (FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("De-fridge");
        stage.show();
    }


    public void closeStage (Stage stage) {
        stage.close();
    }

}
