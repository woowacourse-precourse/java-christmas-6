package christmas;

import christmas.systems.EventSystem;
import christmas.systems.OrderSystem;
import christmas.systems.ReservationSystem;

public class Application {
    public static void main(String[] args) {
        EventSystem eventSystem = new EventSystem();
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        ReservationSystem reservationSystem = new ReservationSystem(orderSystem);
        reservationSystem.process();
    }
}
