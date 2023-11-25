package christmas;

import christmas.domain.ReservationDate;
import christmas.view.InputView;

public class ChristmasController {
    InputView inputView = new InputView();

    public void run(){
        ReservationDate reservationDate = inputView.readDate();
    }
}
