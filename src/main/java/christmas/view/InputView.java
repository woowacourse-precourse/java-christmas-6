package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import christmas.model.RestaurantDatabase;
import christmas.validate.InputValidator;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static java.lang.System.out;

public class InputView {
    private InputValidator validator = new InputValidator();
    private final RestaurantDatabase db = new RestaurantDatabase();
    private int date;


    public int receiveReservationDate() {
        do {
            out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        } while(!promptForReservationDate());

        return date;
    }

    private boolean promptForReservationDate() {
        String dateInput = Console.readLine();

        try {
          validator.validateDateInput(dateInput);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
            return false;
        }

        date = Integer.parseInt(dateInput);
        return true;
    }

    // TODO : 날짜는 누가 가지고 있는 것이 좋을까
    public List<SimpleEntry<Menu, Integer>> receiveMenuToOrder (List<Menu> menu) {
        List<SimpleEntry<Menu, Integer>> result = null;
        do {
            out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            result = promptMenuToOrder(menu);
        } while (result == null);

        return result;
    }

    public List<SimpleEntry<Menu, Integer>> promptMenuToOrder (List<Menu> menu) {
        List<SimpleEntry<Menu, Integer>> result = null;
        try {
            String customerOrderState = Console.readLine().trim();
            List<String> menuOrdered = Arrays.stream(customerOrderState.split(",")).toList();

            result = makeMenuOrderedList(menu, menuOrdered);
            return result;
        }catch ( IllegalStateException e) {
            out.println(e.getMessage());
            return result;
        }
    }

    public List<SimpleEntry<Menu, Integer>> makeMenuOrderedList (List<Menu> menu, List<String> menuOrdered) throws  IllegalStateException{
        List<SimpleEntry<Menu, Integer>> result = new ArrayList<>();

        for (String ordered : menuOrdered) {
           Matcher matcher = validator.validateOrderStateFormat(ordered);
           String menuName = matcher.group(1);
           int cnt = Integer.parseInt(matcher.group(2));

           Menu menuMatched = menu.stream().filter(el -> el.checkMenu(menuName)).toList().get(0);
           result.add(new SimpleEntry<>(menuMatched, cnt));
        }

        validator.hasReachedMaxOrderItems(result);
        return result;
    }




}
