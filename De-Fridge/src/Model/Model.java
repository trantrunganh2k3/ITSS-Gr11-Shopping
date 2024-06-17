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
import java.util.Objects;

public class Model {
    private final ViewFactory viewFactory;
    private static Model model;

    private User user;
    private ObservableList<Ingredient> ingredients;
    private ObservableList<Dish> dishes;
    private ObservableList<User> users;
    private ObservableList<FavoriteRecipe> recipes;
    private ObservableList<ShoppingList> shoppingLists;
    private ObservableList<ShoppingList> sharedShoppingLists;
    private ObservableList<Category> categories;
    private ObservableList<Unit> units;
    private Fridge fridge;
    private List<ShoppingItems> reportShoppingItems;
    private List<Dish> reportDish;
    private ObservableList<User> searchUsers;

    private List<FavoriteRecipe> searchRecipes;


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

    public void setReportShoppingItems () throws SQLException, ClassNotFoundException {
        this.reportShoppingItems = ShoppingListController.getReportItem(getFridge().getFridgeID());
    }

    public List<Dish> getReportDish () throws SQLException, ClassNotFoundException {
        setReportDish();
        return this.reportDish;
    }

    public void setReportDish () throws SQLException, ClassNotFoundException {
        this.reportDish = DishController.getReportDish(getFridge().getFridgeID());
    }

    public List<ShoppingItems> getReportShoppingItems () throws SQLException, ClassNotFoundException {
        setReportShoppingItems();
        return this.reportShoppingItems;
    }


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
        return Objects.requireNonNullElseGet(this.sharedShoppingLists, () -> FXCollections.observableArrayList());
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

    public void searchUser (String searchInput) {
        searchUsers = UserController.findUserByUsername(searchInput);
    }

    public ObservableList<User> getSearchUsers() {
        return searchUsers;
    }

    public ObservableList<User> getUsers(){
        if (users == null) setUser();
        return this.users;
    }

    public void setSearchRecipes (String searchInput) {
        searchRecipes = FavoriteRecipeController.searchRecipe(searchInput);
    }

    public List<FavoriteRecipe> getSearchRecipes() {
        return searchRecipes;
    }

    public void deleteAll (){
        user = null;
        ingredients = null;
        dishes = null;
        users = null;
        recipes = null;
        shoppingLists = null;
        sharedShoppingLists = null;
        categories = null;
        units = null;
        fridge = null;
        reportShoppingItems = null;
        reportDish = null;
        searchUsers = null;
        searchRecipes = null;
    }
}