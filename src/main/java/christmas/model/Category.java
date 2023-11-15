package christmas.model;

public class Category {
    private final int id;
    private final String categoryName;

    public Category (int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public boolean findSameCategory (int i) {
        return i == id;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    public boolean isSame(String category) {
        return this.categoryName.equals(category);
    }
}