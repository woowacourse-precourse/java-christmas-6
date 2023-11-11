package christmas.model;

public class Item {
    private String food;
    private int count;

    public Item(String food, int count) {
        this.food = food;
        this.count = count;
    }

    public static Item extractItem(String input) {
        final int START_INDEX = 0;
        final int NEXT = 1;
        String food = input.substring(START_INDEX, input.indexOf("-"));
        int count = Integer.parseInt(input.substring(input.indexOf("-") + NEXT));

        return new Item(food, count);
    }

    public String getFood() {
        return food;
    }

    public int getCount() {
        return count;
    }
}
