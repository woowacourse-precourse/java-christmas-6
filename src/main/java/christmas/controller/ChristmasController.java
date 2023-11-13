package christmas.controller;

import christmas.controller.view.OutputView;
import christmas.enrtity.Order;
import christmas.enrtity.menu.MenuBoard;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class ChristmasController {

    public void start(){
        MenuBoard menuBoard = new MenuBoard();
        menuBoard.initMenus();
        int visitDate = 28;
        OrderGenerator orderGenerator = new OrderGenerator();
        Order order = orderGenerator.generateOrder(readLine(), visitDate);
        OutputView outputView = new OutputView();
        outputView.printMenu(order);
    }
}
