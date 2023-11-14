package christmas.domain.entity;

import christmas.domain.constants.Enum;
import christmas.domain.entity.menu.MenuBoard;


import java.util.HashMap;
import java.util.Map;

public class Order {
    private int visitDate;
    private static Map<String, Integer> orderMap = new HashMap<>();

    public Order(int visitDate) {
        this.visitDate = visitDate;
    }

    public void putOrderMap(String menu, Integer count){
        validateMenuIsExist(menu);
        validateMenuIsDuplicated(menu);
        validateCount(count);
        orderMap.put(menu, count);
    }

    public Map<String, Integer> getOrderMap(){
        return orderMap;
    }

    public void validateNotOnlyBeverage(){
        for(String key : orderMap.keySet()){
            if(!(MenuBoard.menus.get(key).menuCategory.equals(Enum.MenuCategory.BEVERAGE))){
                return;
            }
        }
        throw new IllegalArgumentException("[ERROR] 음료만 주문할 시, 주문할 수 없습니다.");
    }

    private void validateMenuIsExist(String menu){
        if(MenuBoard.menus.get(menu)==null){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenuIsDuplicated(String menu){
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
