package christmas;

import christmas.manangers.WooWaEventManager;
import christmas.order.OrderSystem;

public class Application {
    public static void main(String[] args) {
        WooWaEventManager wooWaEventManager = new WooWaEventManager();
        OrderSystem orderSystem = new OrderSystem(wooWaEventManager);
        RestaurantInterface restaurantInterface = new RestaurantInterface(orderSystem);
        restaurantInterface.process();
    }
}
