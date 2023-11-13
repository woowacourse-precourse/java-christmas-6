package christmas;

import christmas.model.Reservation;
import christmas.model.RestaurantDatabase;
import christmas.model.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Application {
    static Application app = new Application();
    static RestaurantDatabase db = new RestaurantDatabase();
    static InputView reader = new InputView();
    static OutputView print = new OutputView();

    private List<Menu> menu;
    private Reservation reservation;


    public static void main(String[] args) {
        app.menu = db.loadMenuData();

        app.reserveRestaurant();

    }

    public void reserveRestaurant () {
        print.welcome();

        int date = reader.receiveReservationDate();
        reservation = new Reservation(date);

        List<SimpleEntry<Menu, Integer>> orderedMenu = reader.receiveMenuToOrder(menu);
        app.reservation.orderMenu(orderedMenu);
    }


}
