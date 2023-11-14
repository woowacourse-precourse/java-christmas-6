package christmas.systems;

import static christmas.enums.events.NoEvent.NO_EVENT;
import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.exceptions.RestaurantException;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.utils.StringToDateParser;
import christmas.utils.StringToOrdersParser;
import christmas.views.InputView;
import christmas.views.Messages;
import christmas.views.OutputView;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.function.Function;

public class ReservationProcessor {
    private final ReservationSystem reservationSystem;
    public ReservationProcessor(ReservationSystem reservationSystem) {
        this.reservationSystem = reservationSystem;
    }

    public void process(String restaurantName, Integer year, Integer month){
        ReservationSystem.printAskDate(restaurantName,month);
        LocalDate reservationDate = getInputAndCatchException(
                input -> StringToDateParser.makeReservation(year, month, input));

        ReservationSystem.printAskMenuAndQuantity();
        Orders orders = getInputAndCatchException(StringToOrdersParser::parseInputToOrderSet);
        Receipt receipt = reservationSystem.calculateOrderResult(reservationDate, orders);

        ReservationSystem.printResult(restaurantName,reservationDate, orders, receipt);
        InputView.close();
    }

    private <T> T getInputAndCatchException(Function<String, T> processor) {
        while (true) {
            try {
                String input = InputView.readLine();
                return processor.apply(input);
            } catch (RestaurantException e) {
                OutputView.printException(e);
            }
        }
    }
}
