package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import christmas.validator.InputValidator;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InputView {
    private final InputValidator validator = new InputValidator();
    private int date;


    public int receiveReservationDate() {
        do {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        } while(!promptForReservationDate());

        return date;
    }

    private boolean promptForReservationDate() {
        String dateInput = Console.readLine();

        try {
          validator.validateDateInput(dateInput);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        date = Integer.parseInt(dateInput);
        return true;
    }

    public List<SimpleEntry<Menu, Integer>> receiveMenuToOrder (List<Menu> menu) {
        List<SimpleEntry<Menu, Integer>> result;
        do {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            result = promptMenuToOrder(menu);
        } while (result == null);

        return result;
    }

    private List<SimpleEntry<Menu, Integer>> promptMenuToOrder (List<Menu> menu) {
        List<SimpleEntry<Menu, Integer>> result;
        try {
            String customerOrderState = Console.readLine().trim();
            List<String> menuOrdered = Arrays.stream(customerOrderState.split(",")).toList();

            result = Collections.unmodifiableList(makeMenuOrderedList(menu, menuOrdered));

            validator.checkCategory(result);

        }catch ( IllegalArgumentException e ) {
            result = null;
            System.out.println(e.getMessage());
        }
        return result;
    }

    private List<SimpleEntry<Menu, Integer>> makeMenuOrderedList (List<Menu> menu, List<String> menuOrdered) throws  IllegalArgumentException{
        List<SimpleEntry<Menu, Integer>> result = new ArrayList<>();

            for (String ordered : menuOrdered) {
                SimpleEntry<Menu, Integer> menuMatched = validator.validateOrderStateFormat(menu, ordered);
                result.add(menuMatched);
            }

            validator.checkDuplicatedMenus(result);
            validator.hasReachedMaxOrderItems(result);
            return result;
    }
}
