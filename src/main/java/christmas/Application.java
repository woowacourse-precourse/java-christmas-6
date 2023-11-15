package christmas;

import christmas.model.Menu;
import christmas.model.Reservation;
import christmas.model.RestaurantDatabase;

import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Application {
    private static final Application app = new Application();
    private static final RestaurantDatabase db = new RestaurantDatabase();
    private InputView reader = new InputView();
    private OutputView print = new OutputView();
    private List<Menu> menu;
    private Reservation reservation;


    public static void main(String[] args) {
        app.menu = db.loadMenuData();

        app.reserveRestaurant();
        app.applyPromotion();
        app.previewReservation();
    }

    public void reserveRestaurant () {
        print.welcome();

        int date = reader.receiveReservationDate();
        reservation = new Reservation(date);

        List<SimpleEntry<Menu, Integer>> orderedMenu = reader.receiveMenuToOrder(menu);
        reservation.orderMenu(orderedMenu);
    }

    private void applyPromotion() {
        reservation.applyPromotion();
    }

    private void previewReservation () {
        print.notifyReservationPreview(reservation);

        print.previewOrderedMenu(reservation);
        print.previewTotalAmountBeforeApplying(reservation);

        print.notifyPresentMenu(reservation);
        print.notifyPromotionApplied (reservation);

        print.notifyTotalBenefit(reservation);
        print.notifyPaymentAmountAfterPromotion(reservation);
        print.notifyEventBadge(reservation);
    }

}
