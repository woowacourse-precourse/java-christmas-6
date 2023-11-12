package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.order.OrderSystem;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.utils.StringToDateParser;
import christmas.utils.StringToOrdersParser;
import christmas.views.Messages;
import christmas.views.OutputView;
import java.time.LocalDate;
import java.time.Month;

public class restaurantInterface {
    private final OrderSystem orderSystem;
    private final static Integer month = Month.DECEMBER.getValue();

    public restaurantInterface(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }

    public void process(){

        OutputView.printOut(Messages.announceHello(month));
        OutputView.printOut(Messages.askDate(month));
        String input = this.readLine();
        LocalDate reservationDate = StringToDateParser.makeReservation(2023, Month.DECEMBER.getValue(), input);

        OutputView.printOut(Messages.announceEventBenefit(reservationDate));
        OutputView.printOut(Messages.askMenuAndQuantity());

        input = this.readLine();
        Orders orders = StringToOrdersParser.parseInputToOrderSet(input);
        OutputView.printOut(Messages.announceOrders());
        OutputView.printOut(Messages.repeatAllOrders(orders));

        OutputView.printOut(Messages.announceBeforeDiscount());
        Receipt receipt = orderSystem.orderProcess(reservationDate, orders);
        Integer totalPriceBeforeDiscount = receipt.totalPriceBeforeDiscount();
        OutputView.printOut(Messages.amountBeforeDiscount(totalPriceBeforeDiscount));

        OutputView.printOut(Messages.announceBenefits());
        //OutputView.printOut(Messages.);

        OutputView.printOut(Messages.announceGift());
        //OutputView.printOut(Messages.gift(mainMenu,quantity));

        //
    }

    private String readLine(){
       return Console.readLine();
    }

    private void close(){
        Console.close();
    }
}
