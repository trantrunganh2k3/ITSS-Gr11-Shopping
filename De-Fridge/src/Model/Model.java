package Model;

import Controller.FavoriteRecipe.FavoriteRecipeController;
import View.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final ViewFactory viewFactory;
    private static Model model;

    private ObservableList<Ingredient> ingredients;
    private ObservableList<Dish> dishes;
    private ObservableList<User> Member;

    private ObservableList<User> users;

    private ObservableList<FavoriteRecipe> recipes;

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

    public ObservableList<FavoriteRecipe> getRecipes() {
        if (recipes == null) {
            setRecipes();
        }
        return recipes;
    }

    public void setRecipes() {
        recipes = FXCollections.observableArrayList();
        FavoriteRecipe recipe1 = new FavoriteRecipe();
        FavoriteRecipe recipe2 = new FavoriteRecipe();
        FavoriteRecipe recipe3 = new FavoriteRecipe();

        recipe1.setName("Pho bo");
        recipe1.setDescription("Cach lam");
        recipe1.setIngredient("Beef: 2,5kg\nOnion");

        recipe2.setName("Pizza");
        recipe2.setIngredient("Bough: 500g\nCheese: ...\nTomato source: 2 can");
        recipe2.setDescription("Step by step");

        recipe3.setName("Omelette");
        recipe3.setIngredient("Egg: 2 eggs\nOil: 500ml.");
        recipe3.setDescription("Simple but nutrition.");

        recipes.add(recipe1);
        recipes.add(recipe2);
        recipes.add(recipe3);
    }

    public void setIngredients() {
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();

        ingredient1.setIngredientID(1);
        ingredient1.setName("Banana");
        ingredient1.setQuantity(2);
        ingredient1.setExpiryDay("2024-06-15");
        ingredient1.setUnit("kg");
        ingredient1.setCategory("Fruit");

        ingredient2.setIngredientID(2);
        ingredient2.setName("Lettuce");
        ingredient2.setQuantity(1);
        ingredient2.setExpiryDay("2024-06-15");
        ingredient2.setUnit("kg");
        ingredient2.setCategory("Vegetable");

        ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();;
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        this.ingredients = ingredients;
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
        dish1.setForDate("2024-06-12");
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
        User user1 = new User("admin", "admin", "Admin", "Male", "admin@gmail.com", "Ha Noi", 0, "Active");
        User user2 = new User("trunganh", "123456", "Trung Anh", "Male", "trunganh@gmail.com", "Ha Noi", 0, "Active");
        this.users.add(user1);
        this.users.add(user2);
    }

    public ObservableList<User> getUsers(){
        if (users == null) setUser();
        return this.users;
    }
}