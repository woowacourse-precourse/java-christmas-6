package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashSet;
import java.util.Set;

public class InputView {
    public int readDate() {
        int date;
        while (true) {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = Console.readLine();
            try {
                date = Integer.parseInt(input);
                if (date >= 1 && date <= 31) {
                    break;
                }
                System.out.println("[ERROR] 방문할 날짜는 1 이상 31 이하의 숫자로만 입력받아 주세요.");
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
        return date;
    }


    public String readMenu() {
        while (true) {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            String input = Console.readLine();

            String[] orderTokens = input.split(",");

            if (!hasDuplicateMenu(orderTokens) && isValidOrder(orderTokens) && !isBeverageOnly(orderTokens)) {
                return input;
            }
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isBeverageOnly(String[] orderTokens) {
        for (String order : orderTokens) {
            String[] menuItemAndQuantity = order.split("-");
            String menu = menuItemAndQuantity[0];

            if (!MenuData.beverageItems.contains(menu)) {
                return false;
            }
        }
        return true;
    }


    private boolean hasDuplicateMenu(String[] orderTokens) {
        Set<String> uniqueMenus = new HashSet<>();
        for (String order : orderTokens) {
            String[] menuItemAndQuantity = order.split("-");
            String menu = menuItemAndQuantity[0];

            if (!uniqueMenus.add(menu)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidOrder(String[] orderTokens) {
        for (String order : orderTokens) {
            String[] menuItemAndQuantity = order.split("-");
            if (menuItemAndQuantity.length != 2 || !MenuData.menuMap.containsKey(menuItemAndQuantity[0])) {
                return false;
            }
            try {
                int quantity = Integer.parseInt(menuItemAndQuantity[1]);
                return isValidQuantity(quantity);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                return false;
            }
        }
        return true;
    }

    private boolean isValidQuantity(int quantity) {
        if (quantity < 1) {
            return false;
        }
        return true;
    }

}
