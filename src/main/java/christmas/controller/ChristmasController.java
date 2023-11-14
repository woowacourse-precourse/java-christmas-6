package christmas.controller;

import christmas.domain.entity.Order;
import christmas.domain.generators.OrderGenerator;
import christmas.domain.view.OutputView;


import static camp.nextstep.edu.missionutils.Console.readLine;

public class ChristmasController {

    public void start(){

        int visitDate = 28;
        OrderGenerator orderGenerator = new OrderGenerator();
        Order order = orderGenerator.generateOrder(readLine(), visitDate);
        OutputView outputView = new OutputView();
        outputView.printMenu(order);
    }
}
