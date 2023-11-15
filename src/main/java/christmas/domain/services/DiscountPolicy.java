package christmas.domain.services;

import christmas.domain.constants.Enum;
import christmas.domain.entity.Benefits;
import christmas.domain.entity.Order;
import christmas.domain.entity.menu.Menu;

public class DiscountPolicy {

    Order order;

    public DiscountPolicy(Order order) {
        this.order = order;
    }

    public void dDayDiscount(){
        Benefits.setdDayDiscountAmount(900 + order.getVisitDate() * 100);
    }

    public void weekDayDiscount(){
        for(Menu menu : order.getOrderMap().keySet()){
            if(menu.getMenuCategory().equals(Enum.MenuCategory.DESSERT)){
                Benefits.setWeekdayDiscountAmount
                        (Benefits.getWeekdayDiscountAmount() + order.getOrderMap().get(menu)*2023
                        );
            }
        }
    }

    public void weekendDiscount(){
        for(Menu menu : order.getOrderMap().keySet()){
            if(menu.getMenuCategory().equals(Enum.MenuCategory.MAIN)){
                Benefits.setWeekendDiscountAmount(
                        Benefits.getWeekendDiscountAmount() + order.getOrderMap().get(menu)*2023
                );
            }
        }
    }

    public void specialDiscount(){
        Benefits.setSpecialDayDiscountAmount(1000);
    }

    public void giftEvent(){
        Benefits.setGiftMenu("샴페인");
        Benefits.setGiftEventBenefitAmount(25000);
    }

    public void getDecemberEventBadge(){
        if(Benefits.getTotalBenefitAmount()>=20000){
            Benefits.setDecemberEventBadge("산타");
            return;
        }
        if(Benefits.getTotalBenefitAmount()>=10000){
            Benefits.setDecemberEventBadge("트리");
            return;
        }
        if(Benefits.getTotalBenefitAmount()>=5000){
            Benefits.setDecemberEventBadge("별");
            return;
        }
    }
}
