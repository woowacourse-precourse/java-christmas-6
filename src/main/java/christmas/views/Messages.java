package christmas.views;

import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.order.Order;
import christmas.order.Orders;
import java.util.Set;

public class Messages {
    public static String announceHello(Integer month) {
        return "안녕하세요! " + restaurantName() + " " + convertMonth(month) + " 이벤트 플래너입니다.";
    }

    private static String restaurantName() {
        return "우테코 식당";
    }

    public static String askDate(Integer month) {
        return convertMonth(month) + " 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    }

    public static String askMenuAndQuantity() {
        return "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    }

    public static String announceEventBenefit(Integer month, Integer date) {
        return monthAndDate(month, date) + "에 " + restaurantName() + "에서 받을 이벤트 혜택 미리 보기!";
    }

    public static String announceOrders() {
        return "<주문 메뉴>";
    }

    public static String announceBeforeDiscount() {
        return "<할인 전 총주문 금액>";
    }

    public static String announceGift() {
        return "<증정 메뉴>";
    }

    public static String gift(MainMenu mainMenu,Integer quantity){
        return mainMenu.getName()+" "+quantity+"개";
    }

    public static String amountBeforeDiscount(Integer amount){
        return  convertAmount(amount);
    }

    private static String perOrder(Order order) {
        MenuItem menuItem = order.getMenuItem();
        Integer orderQuantity = order.getOrderQuantity();
        return menuItem.getName() + " " + orderQuantity + "개" + System.lineSeparator();
    }

    public static String repeatAllOrders(Orders orders) {
        Set<Order> orderList = orders.getOrderSet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Order order : orderList) {
            stringBuilder.append(perOrder(order));
        }
        return stringBuilder.toString();
    }

    private static String convertMonth(Integer month) {
        return month + "월";
    }

    private static String convertDate(Integer date) {
        return date + "일";
    }

    private static String convertAmount(Integer amount){
        return NumberFormatter.formatting(amount)+"원";
    }

    private static String monthAndDate(Integer month, Integer date) {
        return convertMonth(month) + " " + convertDate(date);
    }
}
