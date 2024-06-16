package Model;

import View.ViewFactory;
import dbController.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {
    private final ViewFactory viewFactory;
    private static Model model;

    private User user;
    private ObservableList<Ingredient> ingredients;
    private ObservableList<Dish> dishes;
    private ObservableList<User> Member;

    private ObservableList<User> users;

    private ObservableList<FavoriteRecipe> recipes;
    private ObservableList<ShoppingList> shoppingLists;
    private ObservableList<ShoppingList> sharedShoppingLists;

    private ObservableList<Category> categories;
    private ObservableList<Unit> units;
    private Fridge fridge;


    public Model() {
        this.viewFactory = new ViewFactory();
    }


    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void setUser(User user){
        this.user = user;
    }
    public void setFridge() throws SQLException, ClassNotFoundException {this.fridge = FridgeController.getFridge(this.user);}
    public Fridge getFridge () throws SQLException, ClassNotFoundException {
        if (this.fridge == null) {
            setFridge();
        }
        return fridge;
    }

    public User getUser() {return this.user;}


    public void setCategories() {
        categories = CategoryController.listCate();
    }
    public ObservableList<Category> getCategories(){
        if (categories ==null) {
            setCategories();
        }
        return this.categories;
    }
    public void setUnits() {
        units = UnitController.listUnit();
    }
    public ObservableList<Unit> getUnits(){
        if (units ==null) {
            setUnits();
        }
        return this.units;
    }

    public ObservableList<ShoppingList> getShoppingLists() throws SQLException, ClassNotFoundException {
        if (this.shoppingLists == null ) {
            return null;
        }
        return this.shoppingLists;
    }

    public void setSharedShoppingLists(Date date) throws SQLException, ClassNotFoundException {
        sharedShoppingLists = ShoppingListController.getShoppingList(user.getUsername(), date, false);

    }
    public ObservableList<ShoppingList> getSharedShoppingLists() {
        if (this.sharedShoppingLists == null ) {
            return FXCollections.observableArrayList();
        }
        return this.sharedShoppingLists;
    }

    public void setShoppingLists(Date date) throws SQLException, ClassNotFoundException {
        shoppingLists = ShoppingListController.getShoppingList(user.getUsername(),date,true);
    }

    public ObservableList<FavoriteRecipe> getRecipes() {
        if (recipes == null) {
            setRecipes();
        }
        return recipes;
    }

    public void setRecipes() {
        recipes = FXCollections.observableArrayList();
        recipes = FavoriteRecipeController.listRecipe(this.user.getUsername());
    }

    public void setIngredients() throws SQLException, ClassNotFoundException {
        this.ingredients = IngredientController.listIngre(getFridge().getFridgeID());
    }

    public ObservableList<Ingredient> getIngredients() throws SQLException, ClassNotFoundException {
        if (ingredients == null) {
            setIngredients();
        }
        return this.ingredients;
    }
    
    public void setMembers() {
        User member1 = new User();
        User member2 = new User();
      /*  private String username;
        private String password;
        private String name;
        private String gender;
        private String email;
        private String address;
        private int groupID; */

        member1.setName("Duy");
        member1.setUsername("duyvu");
        member1.setEmail("Duy@gmail.com");
        member1.setGroupID(2003);
        
        member2.setName("Duy2");
        member2.setUsername("duyvu2");
        member2.setEmail("Du2y@gmail.com");
        member2.setGroupID(2003);

        ObservableList<User> Member = FXCollections.observableArrayList();;
        Member.add(member1);
        Member.add(member2);

        this.Member = Member;
    }

    public ObservableList<User> getMember() {
        if (Member == null) {
            setMembers();
        }
        return this.Member;
    }

    public ObservableList<Dish> getDishes() {
        return this.dishes;
    }
    public void setDishes(Date date) {
        this.dishes = DishController.listDish(fridge.getFridgeID(),date);
    }

    public void setUser(){
        users = FXCollections.observableArrayList();
        users = UserController.listUser();
    }

    public ObservableList<User> getUsers(){
        if (users == null) setUser();
        return this.users;
    }
}