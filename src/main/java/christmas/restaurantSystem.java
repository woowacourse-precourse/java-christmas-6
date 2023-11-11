package christmas;

import christmas.views.Messages;
import christmas.views.OutputView;
import java.time.Month;

public class restaurantSystem {
    private final static Integer month = Month.DECEMBER.getValue();
    public void process(){

        OutputView.printOut(Messages.announceHello(month));
        OutputView.printOut(Messages.askDate(month));
        //input
        OutputView.printOut(Messages.askMenuAndQuantity());
        //input return 3
        int date =3;
        OutputView.printOut(Messages.announceEventBenefit(month,3));

        OutputView.printOut(Messages.announceOrders());
        OutputView.printOut(Messages.repeatAllOrders(orders));

        OutputView.printOut(Messages.announceBeforeDiscount());
        OutputView.printOut(Messages.amountBeforeDiscount(amount));

        OutputView.printOut(Messages.announceGift());
        OutputView.printOut(Messages.gift(mainMenu,quantity));

        //





    }
}
