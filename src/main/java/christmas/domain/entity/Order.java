package christmas.domain.entity;

import christmas.domain.constants.Enum;
import christmas.domain.entity.menu.Menu;
import christmas.domain.entity.menu.MenuBoard;


import java.util.HashMap;
import java.util.Map;

public class Order {


    private int visitDate;
    private static Map<Menu, Integer> orderMap = new HashMap<>();

    public Map<Menu, Integer> getOrderMap(){
        return orderMap;
    }

    public Order(int visitDate) {
        this.visitDate = visitDate;
    }

    public int getVisitDate() {
        return visitDate;
    }

    public static void emptyMap(){
        orderMap.clear();
    }

    public void putOrderMap(String stringMenu, Integer count){
        validateMenuIsExist(stringMenu);
        validateMenuIsDuplicated(stringMenu);
        validateCount(count);
        Menu menu = MenuBoard.getMenus().get(stringMenu);
        orderMap.put(menu, count);
    }

    public static int getTotalOrderAmount(){
        int totalPrice = 0;
        for(Menu menu : orderMap.keySet()){
            totalPrice += (menu.getPrice() * orderMap.get(menu));
        }
        return totalPrice;
    }

    public void validateNotOnlyBeverage(){
        for(Menu menu : orderMap.keySet()){
            if(menu.getMenuCategory().equals(Enum.MenuCategory.BEVERAGE)){
                return;
            }
        }
        throw new IllegalArgumentException("[ERROR] 음료만 주문할 시, 주문할 수 없습니다.");
    }

    private void validateMenuIsExist(String stringMenu){
        if(MenuBoard.getMenus().get(stringMenu)==null){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenuIsDuplicated(String stringMenu){
        Menu menu = MenuBoard.getMenus().get(stringMenu);
        if(orderMap.get(menu)!=null){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateCount(Integer count){
        if(count<=0){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
