package christmas.controller;

import christmas.domain.entity.Day;
import christmas.domain.entity.Order;
import christmas.domain.generators.OrderGenerator;
import christmas.domain.util.Printer;
import christmas.domain.view.InputView;
import christmas.domain.view.OutputView;


import static camp.nextstep.edu.missionutils.Console.readLine;

public class ChristmasController {

    OutputView outputView = new OutputView(new Printer());
    InputView inputView = new InputView(new Printer());
    Printer printer = new Printer();
    OrderGenerator orderGenerator = new OrderGenerator();

    public void start() {

        outputView.printPreface();

        int visitDate;

        while (true) {
            try {
                visitDate = inputView.readDate();
                Day.validateIsDate(visitDate);
                break;
            } catch (IllegalArgumentException e) {
                printer.println(e.getMessage());
            }
        }

        String orderString;
        Order order;
        while (true){
            try {
                orderString = inputView.readMenu();
                order = orderGenerator.generateOrder(orderString, visitDate);
                break;
            } catch (IllegalArgumentException e){
                Order.emptyMap();
                printer.println(e.getMessage());
            }
        }


        outputView.printMenu(order);
    }
}
