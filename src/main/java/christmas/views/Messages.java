package christmas.views;

import christmas.enums.menu.MenuItem;
import christmas.event.EventResult;
import christmas.order.Order;
import christmas.order.Orders;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Messages {
    public static String announceHello(String restaurantName ,Integer month) {
        return "안녕하세요! " + restaurantName + " " + convertMonth(month) + " 이벤트 플래너입니다.";
    }

    public static String askDate(Integer month) {
        return convertMonth(month) + " 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    }

    public static String askMenuAndQuantity() {
        return "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    }

    public static String announceEventBenefit(String restaurantName,LocalDate localDate) {
        int date = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        return monthAndDate(month, date) + "에 " + restaurantName + "에서 받을 이벤트 혜택 미리 보기!";
    }

    public static String announceOrders() {
        return "<주문 메뉴>";
    }

    public static String announceBeforeDiscount() {
        return "<할인 전 총주문 금액>";
    }

    public static String announceEventBenefits() {
        return "<혜택 내역>";
    }

    public static String perEventBenefit(List<EventResult> eventResults){
        StringBuilder stringBuilder = new StringBuilder();
        for (EventResult eventResult : eventResults) {
            String result = eventResult.events().getName() + " " + convertAmount(-eventResult.discountBenefit()) + System.lineSeparator();
            stringBuilder.append(result);
        }
        return stringBuilder.toString();
    }

    public static String announceGift() {
        return "<증정 메뉴>";
    }
    public static String announceTotalDiscountBenefit() {
        return "<총혜택 금액>";
    }

    public static String announceEventBadge(Integer month) {
        return "<"+month+"월 이벤트 배지>";
    }

    public static String gift(MenuItem menuItem,Integer quantity){
        return menuItem.getName()+" "+quantity+"개";
    }

    public static String showAmount(Integer amount){
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
