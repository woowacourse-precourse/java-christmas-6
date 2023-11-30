package christmas.domain;

public class Order {
    static final int FOOD_INDEX = 0;
    static final int QUANTITY_INDEX = 1;
    static final int MINIMUM_ORDER_QUANTITY = 1;
    static final int CORRECT_ORDER_LENGTH = 2;
    static final int ZERO = 0;

    private final String food;
    private final int quantity;

    public Order(String inputOrder) throws IllegalArgumentException {
        String[] foodAndQuantity = inputOrder.split("-");
        validateInput(foodAndQuantity);

        String food = foodAndQuantity[FOOD_INDEX];
        int quantity = Integer.parseInt(foodAndQuantity[QUANTITY_INDEX]);
        validateQuantity(quantity);
        validateFood(food);

        this.quantity = quantity;
        this.food = food;
    }

    private void validateInput(String[] foodAndQuantity) throws IllegalArgumentException {
        if(foodAndQuantity.length != CORRECT_ORDER_LENGTH){
            throw new IllegalArgumentException();
        }
    }

    private void validateQuantity(int quantity) throws IllegalArgumentException {
        if(quantity < MINIMUM_ORDER_QUANTITY){
            throw new IllegalArgumentException();
        }
    }

    private void validateFood(String food) throws IllegalArgumentException{
        boolean isInMenu = false;
        for(Menu menu : Menu.values()){
            if(menu.isFoodOnMenu(food)){
                isInMenu = true;
            }
        }
        if(!isInMenu){
            throw new IllegalArgumentException();
        }
    }

    public boolean isSame(Order order){
        boolean isSame = food.equals(order.getFood());

        return isSame;
    }

    private String getFood(){
        return food;
    }

    public int calculate() {
        int price = ZERO;

        for(Menu menu : Menu.values()) {
            price = menu.cost(food);

            if(isPriceChanged(price)){
                break;
            }
        }

        return price * quantity;
    }

    private boolean isPriceChanged(int price){
        if(price != ZERO){
            return true;
        }

        return false;
    }

    public int getDessertCount() {
        int dessertCount = 0;
        for(Menu menu : Menu.values()) {
            if(menu.findMenuType(food) == "dessert"){
                dessertCount = quantity;
                break;
            }
        }

        return dessertCount;
    }
}