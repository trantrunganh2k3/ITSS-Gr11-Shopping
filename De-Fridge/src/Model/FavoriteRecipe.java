package Model;

public class FavoriteRecipe {
    private int recipeID;
    private String name;
    private String description;
    private String ingredient;
    private String username;

    public FavoriteRecipe(){}

    public FavoriteRecipe(int recipeID, String name, String description, String ingredient, String username){
        this.recipeID = recipeID;
        this.name = name;
        this.description = description;
        this.ingredient = ingredient;
        this.username = username;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
