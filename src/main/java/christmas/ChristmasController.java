package christmas;

import christmas.view.InputView;

public class ChristmasController {
    InputView inputView = new InputView();

    public void run(){
        int reservationDate = inputView.readDate();
    }
}
