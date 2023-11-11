package christmas.func;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrintAdvantageList {
    public void printDiscountSummary(int visitDate, Map<String, Integer> order, boolean isEvent, int accountAmount) {
        if (isEvent == true) {
            System.out.println("<혜택 내역>");
            christmasSpectialDiscount(visitDate);
            weekdayDiscount(visitDate, order);
        }
        if (isEvent == false) {
            noBenefit(accountAmount);
        }
    }

    private void weekdayDiscount(int visitDate, Map<String, Integer> order) {
        if (visitDate >= 3 && visitDate <= 7 || visitDate >= 10 && visitDate <= 14 ||
                visitDate >= 17 && visitDate <= 21 || visitDate >= 24 && visitDate <= 28 || visitDate == 31) {
            Set<String> dessertItems = new HashSet<>(Arrays.asList("초코케이크", "아이스크림"));
            int discountAmount = 0;

            for (Map.Entry<String, Integer> entry : order.entrySet()) {
                String itemName = entry.getKey();
                int quantity = entry.getValue();
                discountAmount += isDisert(dessertItems, itemName, quantity);
            }
            System.out.println("평일 할인: -" + discountAmount);
        }
    }

    private int isDisert(Set<String> dessertItems, String itemName, int quantity) {
        int total = 0;
        if (dessertItems.contains(itemName)) {
            int dessertDiscount = 2023 * quantity;
            total += dessertDiscount;
        }
        return total;
    }

    private void noBenefit(int accountAmount) {
        System.out.println("<혜택 내역>");
        System.out.println("없음");
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println("0원");
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(accountAmount + "원");
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println("없음");
    }

    private void christmasSpectialDiscount(int visitDate) {
        if (visitDate >= 1 && visitDate <= 25) {
            int discountAmount = 900;
            int daysUntilChristmas = 26 - visitDate;

            discountAmount += (26 - daysUntilChristmas) * 100;
            System.out.println("크리스마스 디데이 할인: -" + discountAmount + "원");
        }
    }

}
