package christmas.systems.reservationsystem;

import christmas.exceptions.RestaurantException;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.systems.reservationsystem.ReservationSystem;
import christmas.utils.StringToDateParser;
import christmas.utils.StringToOrdersParser;
import christmas.views.InputView;
import christmas.views.OutputView;
import java.time.LocalDate;
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
