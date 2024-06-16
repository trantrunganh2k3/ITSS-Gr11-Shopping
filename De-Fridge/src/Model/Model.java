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

    public ObservableList<ShoppingList> getShoppingLists() {
        if (this.shoppingLists == null ) {
            setShoppingLists();
        }
        return this.shoppingLists;
    }

    public void setSharedShoppingLists() {
        ShoppingList shoppingList = new ShoppingList();

        shoppingList.setListName("Today list");
        shoppingList.setDate(Date.valueOf("2024-06-15"));

        ShoppingItems item1 = new ShoppingItems();
        ShoppingItems item2 = new ShoppingItems();
        ShoppingItems item3 = new ShoppingItems();

        item1.setItemName("Rau");
        item1.setCategory("Veggies");
        item1.setQuantity(1);
        item1.setUnit("Kg");
        item1.setBoughtBy("Me");

        item2.setItemName("Dua hau");
        item2.setCategory("Fruit");
        item2.setQuantity(2);
        item2.setUnit("Qua");


        item3.setItemName("Thit bo");
        item3.setCategory("Meat");
        item3.setQuantity(500);
        item3.setUnit("gram");

        ObservableList<ShoppingItems> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        shoppingList.setShoppingItems(items);
        for (ShoppingItems item: shoppingList.getShoppingItems()){
            System.out.println(item.getBoughtBy());
        }
        sharedShoppingLists = FXCollections.observableArrayList();
        sharedShoppingLists.add(shoppingList);

    }
    public ObservableList<ShoppingList> getSharedShoppingLists() {
        if (this.sharedShoppingLists == null ) {
            setSharedShoppingLists();
        }
        return this.sharedShoppingLists;
    }

    public void setShoppingLists() {
        ShoppingList shoppingList = new ShoppingList();

        shoppingList.setListName("Today list");
        //shoppingList.setDate();

        ShoppingItems item1 = new ShoppingItems();
        ShoppingItems item2 = new ShoppingItems();
        ShoppingItems item3 = new ShoppingItems();

        item1.setItemName("Rau");
        item1.setCategory("Veggies");
        item1.setQuantity(1);
        item1.setUnit("Kg");
        item1.setBoughtBy("Me");

        item2.setItemName("Dua hau");
        item2.setCategory("Fruit");
        item2.setQuantity(2);
        item2.setUnit("Qua");


        item3.setItemName("Thit bo");
        item3.setCategory("Meat");
        item3.setQuantity(500);
        item3.setUnit("gram");

        ObservableList<ShoppingItems> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        shoppingList.setShoppingItems(items);
        for (ShoppingItems item: shoppingList.getShoppingItems()){
            System.out.println(item.getBoughtBy());
        }
        shoppingLists = FXCollections.observableArrayList();
        shoppingLists.add(shoppingList);

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

    public void setIngredients() {
        this.ingredients = IngredientController.listIngre();
    }

    public ObservableList<Ingredient> getIngredients() {
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
        if (this.dishes == null) {
            setDishes();
        }
        return this.dishes;
    }
    public void setDishes() {
        dishes = FXCollections.observableArrayList();
        Dish dish1 = new Dish();
        Dish dish3 = new Dish();
        Dish dish2 = new Dish();

        DishIngredient ingredient = new DishIngredient();
        ingredient.setUnit("kg");
        ingredient.setIngredientId(1);
        ingredient.setIngredientName("Banana");
        ingredient.setQuantity(1);
        List<DishIngredient> list = new ArrayList<>();
        list.add(ingredient);

        dish1.setMeal("Breakfast");
        dish1.setName("Cereal");
        dish1.setForDate(Date.valueOf("2024-06-12"));
        dish1.setIngredients(list);

        /*dish2.setMeal("Lunch");
        dish2.setName("Pho");
        dish2.setForDate("2024-06-12");

        dish3.setMeal("Dinner");
        dish3.setName("Pizza");
        dish3.setForDate("2024-06-12");*/

        this.dishes.add(dish1);
        //this.dishes.add(dish2);
        //this.dishes.add(dish3);
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