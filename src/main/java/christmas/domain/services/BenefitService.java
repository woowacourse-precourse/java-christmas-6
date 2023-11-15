package christmas.domain.services;

import christmas.domain.constants.DiscountPolicyEnum;
import christmas.domain.entity.Benefits;
import christmas.domain.util.DayUtil;
import christmas.domain.entity.Order;

public class BenefitService {
    Order order;
    DiscountPolicy discountPolicy;

    public BenefitService(Order order) {
        this.order = order;
        discountPolicy = new DiscountPolicy(order);
    }

    public void benefitApplication(){
        Benefits.setTotalAmountBeforeDiscount(Order.getTotalOrderAmount());

        if(DayUtil.isBeforeDday(order.getVisitDate()) && isAvailableDiscount()){
            discountPolicy.dDayDiscount();
        }
        if(DayUtil.isWeekday(order.getVisitDate()) && isAvailableDiscount()){
            discountPolicy.weekDayDiscount();
        }
        if(DayUtil.isWeekend(order.getVisitDate()) && isAvailableDiscount()){
            discountPolicy.weekendDiscount();
        }
        if(DayUtil.isSpecialDay(order.getVisitDate()) && isAvailableDiscount()){
            discountPolicy.specialDiscount();
        }
        if(Benefits.getTotalAmountBeforeDiscount()>= DiscountPolicyEnum.Discount.APPLIED_AMOUNT_GET_GIFT.getValue()){
            discountPolicy.giftEvent();
        }
        if(isAvailableDiscount()){
            Benefits.accountTotalBenefit();
            Benefits.accountTotalAmountAfterDiscount();
            discountPolicy.getDecemberEventBadge();
        }
    }

    private boolean isAvailableDiscount(){
        if(Benefits.getTotalAmountBeforeDiscount()>=DiscountPolicyEnum.Discount.MINIMUM_AMOUNT_TO_DISCOUNT.getValue()){
            return true;
        }
        return false;
    }
}