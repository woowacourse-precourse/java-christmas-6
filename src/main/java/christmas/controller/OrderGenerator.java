package christmas.controller;

import christmas.enrtity.Order;

import java.util.List;

public class OrderGenerator {
    public Order generateOrder(String readLine, int date){
        Order order = new Order(date);
        List<String> stringList = List.of(readLine.replaceAll("\\s", "").split(","));
        for(String str : stringList){
            String[] strArray = str.split("-");
            int menuCount;
            if(strArray.length>2){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            try{
                menuCount = Integer.parseInt(strArray[1]);
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            order.putOrderMap(strArray[0],menuCount);
        }
        return order;
    }
}
