package christmas.domain.services;

import christmas.domain.constants.DiscountPolicyEnum;
import christmas.domain.constants.MenuCategoryEnum;
import christmas.domain.constants.MenuEnum;
import christmas.domain.entity.Benefits;
import christmas.domain.entity.Order;
import christmas.domain.entity.menu.Menu;

public class DiscountPolicy {

    Order order;

    public DiscountPolicy(Order order) {
        this.order = order;
    }

    public void dDayDiscount(){
        Benefits.setdDayDiscountAmount(
                DiscountPolicyEnum.Discount.D_DAY_START_DISCOUNTING_AMOUNT.getValue()
                        + order.getVisitDate()
                        * DiscountPolicyEnum.Discount.D_DAY_DAILY_DISCOUNTING_AMOUNT.getValue());
    }

    public void weekDayDiscount(){
        for(Menu menu : order.getOrderMap().keySet()){
            if(menu.getMenuCategory().equals(MenuCategoryEnum.MenuCategory.DESSERT)){
                Benefits.setWeekdayDiscountAmount(
                        Benefits.getWeekdayDiscountAmount()
                                + order.getOrderMap().get(menu)
                                * DiscountPolicyEnum.Discount.WEEKLY_DISCOUNTING_AMOUNT.getValue()
                        );
            }
        }
    }

    public void weekendDiscount(){
        for(Menu menu : order.getOrderMap().keySet()){
            if(menu.getMenuCategory().equals(MenuCategoryEnum.MenuCategory.MAIN)){
                Benefits.setWeekendDiscountAmount(
                        Benefits.getWeekendDiscountAmount()
                                + order.getOrderMap().get(menu)
                                * DiscountPolicyEnum.Discount.WEEKLY_DISCOUNTING_AMOUNT.getValue()
                );
            }
        }
    }

    public void specialDiscount(){
        Benefits.setSpecialDayDiscountAmount(DiscountPolicyEnum.Discount.SPECIAL_DISCOUNTING_AMOUNT.getValue());
    }

    public void giftEvent(){
        Benefits.setGiftMenu(MenuEnum.Menus.CHAMPAGNE.getName());
        Benefits.setGiftEventBenefitAmount(MenuEnum.Menus.CHAMPAGNE.getPrice());
    }

    public void getDecemberEventBadge(){
        if(Benefits.getTotalBenefitAmount()>=DiscountPolicyEnum.Discount.APPLIED_DISCOUNT_AMOUNT_GET_SANTA.getValue()){
            Benefits.setDecemberEventBadge(DiscountPolicyEnum.Badge.SANTA_BADGE.getName());
            return;
        }
        if(Benefits.getTotalBenefitAmount()>=DiscountPolicyEnum.Discount.APPLIED_DISCOUNT_AMOUNT_GET_TREE.getValue()){
            Benefits.setDecemberEventBadge(DiscountPolicyEnum.Badge.TREE_BADGE.getName());
            return;
        }
        if(Benefits.getTotalBenefitAmount()>=DiscountPolicyEnum.Discount.APPLIED_DISCOUNT_AMOUNT_GET_STAR.getValue()){
            Benefits.setDecemberEventBadge(DiscountPolicyEnum.Badge.STAR_BADGE.getName());
        }
    }
}
