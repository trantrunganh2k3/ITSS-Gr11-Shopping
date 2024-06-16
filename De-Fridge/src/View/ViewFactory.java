package View;

import Controller.Admin.CateDetailController;
import Controller.Admin.UserDetailController;
import Controller.FavoriteRecipe.RecipeDetailController;
import Controller.Fridge.AddDishController;
import Controller.Fridge.DishDetailController;
import Controller.Group.AddMemberController;
import Controller.ShoppingList.AddListController;
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
    private BorderPane groupView;
    private AnchorPane groupMemberView;
    private AnchorPane groupReportView;

    private AnchorPane myListView;

    private AnchorPane sharedListView;

    private AnchorPane listUserView;

    private AnchorPane accInfoView;
    private AnchorPane cateUnitView;

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
            try {
                fridgeItemsView = new FXMLLoader(getClass().getResource("fxml/fridge/fridgeItem.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
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

    public AnchorPane getMyListView() {
        if (myListView == null) {
            try {
                myListView = new FXMLLoader(getClass().getResource("fxml/shoppingList/myList.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return myListView;
    }

    public AnchorPane getSharedListView() {
        if (sharedListView == null) {
            try {
                sharedListView = new FXMLLoader(getClass().getResource("fxml/shoppingList/sharedList.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sharedListView;
    }




    public void showUserAccDetail(UserDetailController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/admin/userDetail.fxml"));
        loader.setController(controller);
        createStage(loader);
    }



    public void showRecipeDetail (RecipeDetailController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/favoriteRecipes/recipeDetail.fxml"));
        loader.setController(controller);
        createStage(loader);
    }


    public void showAddDishView(AddDishController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/fridge/addDish.fxml"));
        loader.setController(controller);
        createStage(loader);
    }

    public void showDishDetailView(DishDetailController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/fridge/dishDetail.fxml"));
        loader.setController(controller);
        createStage(loader);
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
    public BorderPane getGroupView () {
        if (groupView == null) {
            try {
                groupView = new FXMLLoader(getClass().getResource("fxml/group/group.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.groupView;
    }
    public AnchorPane getGroupMemberView() {
        if (groupMemberView == null) {
            try {
                groupMemberView = new FXMLLoader(getClass().getResource("fxml/group/groupMember.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return groupMemberView;
    }
    public void showAddMemberView(AddMemberController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/group/addMember.fxml"));
        loader.setController(controller);
        createStage(loader);
    }
    
    public AnchorPane getGroupReportView() {
        if (groupReportView == null) {
            try {
                groupReportView = new FXMLLoader(getClass().getResource("fxml/group/groupReport.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.groupReportView;
    }

    public void showAddList(AddListController controller) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/shoppingList/addList.fxml"));
        loader.setController(controller);
        createStage(loader);
    }


    public AnchorPane getAccInfoView(){
        if (accInfoView == null){
            try{
                accInfoView = new FXMLLoader(getClass().getResource("fxml/accInfo.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.accInfoView;
    }

    public void showCateDetail (CateDetailController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/admin/cateDetail.fxml"));
        loader.setController(controller);
        createStage(loader);
    }

    public AnchorPane getCateUnitView(){
        if (cateUnitView == null){
            try{
                cateUnitView = new FXMLLoader(getClass().getResource("fxml/admin/cateUnit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.cateUnitView;
    }

    public void showHome() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/home.fxml"));
        createStage(loader);
    }

    public void showAdHome(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/homeAd.fxml"));
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

    public AnchorPane getListUserView() {
        if (listUserView == null) {
            try {
                listUserView = new FXMLLoader(getClass().getResource("fxml/admin/userAccounts.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.listUserView;
    }
}
