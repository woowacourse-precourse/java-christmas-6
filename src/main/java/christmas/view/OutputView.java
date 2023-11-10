package christmas.view;

public class OutputView {
    private static OutputView outputView;

    private OutputView() {

    }

    public static OutputView getInstance() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }
    // ...
}