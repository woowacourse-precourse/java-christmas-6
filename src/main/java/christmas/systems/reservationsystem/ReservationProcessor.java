package christmas.systems.reservationsystem;

import christmas.exceptions.RestaurantException;
import christmas.order.Orders;
import christmas.order.Receipt;
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
        String printAskDate = ReservationSystem.printAskDate(restaurantName, month);
        OutputView.printOut(printAskDate);
        LocalDate reservationDate = getInputAndCatchException(
                input -> StringToDateParser.makeReservation(year, month, input));

        String printAskMenuAndQuantity = ReservationSystem.printAskMenuAndQuantity();
        OutputView.printOut(printAskMenuAndQuantity);
        Orders orders = getInputAndCatchException(StringToOrdersParser::parseInputToOrderSet);
        Receipt receipt = reservationSystem.calculateOrderResult(reservationDate, orders);

        String result = ReservationSystem.printResult(restaurantName, reservationDate, orders, receipt);
        OutputView.printOut(result);
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
