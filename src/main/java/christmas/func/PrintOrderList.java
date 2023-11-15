package christmas.func;

import static christmas.view.MenuData.menuMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrintOrderList {
    private Map<String, Integer> orderSummary;
    private int totalBeforeDiscount;

    public void printOrderSummary(String orderMenu) {
        String[] menuItems = orderMenu.split(",");
        orderSummary = new HashMap<>();

        System.out.println("<주문 메뉴>");
        for (String menuItem : menuItems) {
            String[] parts = menuItem.split("-");
            if (parts.length == 2) {
                String itemName = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                orderSummary.put(itemName, quantity);
                System.out.println(itemName + " " + quantity + "개");
            }
        }
        System.out.println("");
        beforeDiscount(orderSummary);
    }

    public Map<String, Integer> getOrderSummary() {
        return orderSummary;
    }

    private void beforeDiscount(Map<String, Integer> orderSummary) {
        totalBeforeDiscount = 0;
        System.out.println("<할인 전 총주문 금액>");
        for (Map.Entry<String, Integer> entry : orderSummary.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            if (menuMap.containsKey(itemName)) {
                int itemPrice = menuMap.get(itemName);
                int itemTotalPrice = itemPrice * quantity;
                totalBeforeDiscount += itemTotalPrice;
            }
        }
        System.out.println(totalBeforeDiscount + "원");
        serviceProduct(totalBeforeDiscount);
    }

    public boolean benefitPossible() {
        if (totalBeforeDiscount >= 10000) {
            return true;
        }
        return false;
    }

    public int getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    private void serviceProduct(int totalBeforeDiscount) {

        if (totalBeforeDiscount >= 120000) {
            System.out.println("");
            System.out.println("<증정 메뉴>");
            System.out.println("샴페인 1개");
        }
    }

}
