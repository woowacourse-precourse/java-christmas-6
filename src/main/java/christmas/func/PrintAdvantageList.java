package christmas.func;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrintAdvantageList {

    private int totalDiscount = 0;

    public void printDiscountSummary(int visitDate, Map<String, Integer> order, boolean isEvent, int accountAmount) {
        if (isEvent == true) {
            System.out.println();
            System.out.println("<혜택 내역>");
            christmasSpecialDiscount(visitDate);
            weekdayDiscount(visitDate, order);
            weekendDiscount(visitDate, order);
            specialEvent(visitDate);
            presentEvent(accountAmount);
            totalBenefit(accountAmount);
        }
        if (isEvent == false) {
            noBenefit(accountAmount);
        }
    }

    private void totalBenefit(int accoutAmount) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println("-" + totalDiscount + "원");
        System.out.println();
        expectAmount(accoutAmount);
        System.out.println();
        eventBadge();
    }

    private void expectAmount(int accoutAmount) {
        int afterDiscount = accoutAmount - totalDiscount;
        if (accoutAmount >= 120000) {
            afterDiscount += 25000;
            System.out.println("<할인 후 예상 결제 금액>");
            System.out.println(afterDiscount + "원");
        }
        if (accoutAmount < 120000){
            System.out.println("<할인 후 예상 결제 금액>");
            System.out.println(afterDiscount + "원");
        }
    }

    private void eventBadge() {
        if (totalDiscount < 5000) {
            System.out.println("<12월 이벤트 배지>");
            System.out.println("없음");
        }
        if (totalDiscount >= 5000 && totalDiscount < 10000) {
            System.out.println("<12월 이벤트 배지>");
            System.out.println("별");
        }
        if (totalDiscount >= 10000 && totalDiscount < 20000) {
            System.out.println("<12월 이벤트 배지>");
            System.out.println("트리");
        }
        if (totalDiscount >= 20000) {
            System.out.println("<12월 이벤트 배지>");
            System.out.println("산타");
        }

    }

    private void presentEvent(int accountAmount) {
        if (accountAmount >= 120000) {
            int presentDiscount = 25000;
            totalDiscount += presentDiscount;
            System.out.println("증정 이벤트 : -25,000");
        }
    }

    private void specialEvent(int visitDate) {
        if (visitDate == 3 || visitDate == 10 || visitDate == 17 || visitDate >= 24 && visitDate <= 25
                || visitDate == 31) {
            int specialDiscount = 1000;
            totalDiscount += specialDiscount;
            System.out.println("특별할인: -1,000원");
        }
    }

    private void weekendDiscount(int visitDate, Map<String, Integer> order) {
        if (visitDate >= 1 && visitDate <= 2 || visitDate >= 8 && visitDate <= 9 ||
                visitDate >= 15 && visitDate <= 16 || visitDate >= 22 && visitDate <= 23
                || visitDate >= 29 && visitDate <= 30) {
            Set<String> mainItems = new HashSet<>(Arrays.asList("티본스테이크", "바베큐립", "해산물파스타", "크리스마스파스타"));
            int discountAmount = 0;

            for (Map.Entry<String, Integer> entry : order.entrySet()) {
                String itemName = entry.getKey();
                int quantity = entry.getValue();
                discountAmount += isDisert(mainItems, itemName, quantity);
            }
            System.out.println("주말 할인: -" + discountAmount);
            totalDiscount += discountAmount;
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
            System.out.println("평일 할인: -" + discountAmount + "원");
            totalDiscount += discountAmount;
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
        System.out.println();
        System.out.println("<증정메뉴>");
        System.out.println("없음");
        System.out.println();
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

    private void christmasSpecialDiscount(int visitDate) {
        if (visitDate >= 1 && visitDate <= 25) {
            int discountAmount = 900;
            int daysUntilChristmas = 26 - visitDate;

            discountAmount += (26 - daysUntilChristmas) * 100;
            System.out.println("크리스마스 디데이 할인: -" + discountAmount + "원");
            totalDiscount += discountAmount;
        }
    }

}
