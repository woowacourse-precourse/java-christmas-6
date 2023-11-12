package christmas;

import christmas.manangers.BadgeManager;
import christmas.manangers.WooWaEventManager;
import christmas.order.OrderSystem;

public class Application {
    public static void main(String[] args) {
        WooWaEventManager wooWaEventManager = new WooWaEventManager();
        BadgeManager badgeManager = new BadgeManager();
        OrderSystem orderSystem = new OrderSystem(wooWaEventManager,badgeManager);
        RestaurantInterface restaurantInterface = new RestaurantInterface(orderSystem);
        restaurantInterface.process();
    }
}
