package christmas.event;

import static org.junit.jupiter.api.Assertions.*;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.weekdiscount.WeekendDiscount;
import christmas.order.OrderMenu;
import christmas.order.Orders;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Test;

class WooWaEventHandlerTest {

    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK, 2);
    private final static Orders ordersWithDessert = new Orders(List.of(orderMenuWithDessert));
    private final static Orders ordersWithMain = new Orders(List.of(orderMenuWithMain));


    @Test
    void activateEvent() {
        WooWaEventHandler wooWaEventHandler = new WooWaEventHandler();
        EventBenefit eventBenefit = wooWaEventHandler.activateEvent(LocalDate.of(2023, Month.DECEMBER, 3),
                ordersWithMain);
        Integer integer = eventBenefit.discountBenefit();
        System.out.println("integer = " + integer);
        MenuItem gift = eventBenefit.gift();
        System.out.println("gift = " + gift);

    }
}