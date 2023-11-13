package christmas;

import christmas.model.DataLoader;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    static Application app = new Application();
    static DataLoader db = new DataLoader();
    static InputView reader = new InputView();
    static OutputView print = new OutputView();

    public static void main(String[] args) {
        db.loadMenuData();
        app.Customer();


    }

    public void Customer () {
        print.welcome();
        reader.receiveReservationDate();


    }
}
