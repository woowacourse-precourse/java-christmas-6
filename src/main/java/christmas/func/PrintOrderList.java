package christmas.func;

import static christmas.view.MenuData.menuMap;

import java.util.HashMap;
import java.util.Map;

public class PrintOrderList {

    public void printOrderSummary(String orderMenu) {
        String[] menuItems = orderMenu.split(",");
        Map<String, Integer> orderSummary = new HashMap<>();

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

    private void beforeDiscount(Map<String, Integer> orderSummary) {
        int totalBeforeDiscount = 0;
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

    private void serviceProduct(int totalBeforeDiscount) {

        if (totalBeforeDiscount >= 120000) {
            System.out.println("");
            System.out.println("<증정 메뉴>");
            System.out.println("샴페인 1개");
        }
    }

}
