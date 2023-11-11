package christmas.model;

import java.util.List;

public class Order {
    private List<Item> items;
    private int date;

    public Order(List<Item> items, int date) {
        this.items = items;
        this.date = date;
    }

    public int sumAmount() {
        int sum = 0;
        for(Item item : items) {
            String food = item.getFood();
            sum += FoodPrice.valueOf(food).getPrice();
        }
        return sum;
    }

    public int getDessertCount() {
        final String TYPE_DESSERT = "dessert";
        int count = 0;

        for(Item item : items) {
            String type = FoodType.valueOf(item.getFood()).getType();
            if(type.equals(TYPE_DESSERT)) {
                count++;
            }
        }
        return count;
    }

    public int getMainCount() {
        final String TYPE_MAIN = "main";
        int count = 0;

        for(Item item : items) {
            String type = FoodType.valueOf(item.getFood()).getType();
            if(type.equals(TYPE_MAIN)) {
                count++;
            }
        }
        return count;
    }
}
