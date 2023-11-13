package christmas;

import christmas.systems.event.EventInitializer;
import christmas.systems.event.EventSystem;
import christmas.systems.OrderSystem;
import christmas.systems.ReservationSystem;

public class Application {
    public static void main(String[] args) {
        EventInitializer eventInitializer = new EventInitializer();
        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        ReservationSystem reservationSystem = new ReservationSystem(orderSystem);
        reservationSystem.process();
    }
}
