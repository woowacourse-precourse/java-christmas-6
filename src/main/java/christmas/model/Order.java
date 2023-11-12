package christmas.model;

import java.util.List;

public class Order {
    private List<Item> items;
    private int date;

    public Order(int date, List<Item> items) {
        this.date = date;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getDate() {
        return date;
    }

    public int sumAmount() {
        int sum = 0;
        for (Item item : items) {
            String food = item.getFood();
            int count = item.getCount();
            sum += FoodPrice.valueOf(food).getPrice() * count;
        }
        return sum;
    }

    public int getDessertCount() {
        final String TYPE_DESSERT = "Dessert";
        int count = 0;

        for (Item item : items) {
            String type = FoodType.valueOf(item.getFood()).getType();
            if (type.equals(TYPE_DESSERT)) {
                count += item.getCount();
            }
        }
        return count;
    }

    public int getMainCount() {
        final String TYPE_MAIN = "Main";
        int count = 0;

        for (Item item : items) {
            String type = FoodType.valueOf(item.getFood()).getType();
            if (type.equals(TYPE_MAIN)) {
                count += item.getCount();
            }
        }
        return count;
    }
}
