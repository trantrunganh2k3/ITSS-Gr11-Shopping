package Model;

import java.util.List;

public class Category {
    private int id;
    private String category;

    private List<Unit> units;

    public Category(){}

    public Category(int id, String category){
        this.id = id;
        this.category = category;
    }

    public Category(int id, String category, List<Unit> units){
        this.id = id;
        this.category = category;
        this.units = units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<Unit> getUnits() {return this.units;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
