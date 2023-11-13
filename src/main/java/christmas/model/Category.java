package christmas.model;

public class Category {
    private int id;
    private String category;

    public Category (int id, String category) {
        this.id = id;
        this.category = category;
    }

    public boolean findSameCategory (int i ) {
        if (i == id) return true;
        return false;
    }

    @Override
    public String toString() {
        return  ""+ category;
    }
}