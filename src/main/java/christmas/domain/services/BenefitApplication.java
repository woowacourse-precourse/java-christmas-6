package christmas.domain.services;

import christmas.domain.entity.Benefits;
import christmas.domain.entity.Day;
import christmas.domain.entity.Order;

public class BenefitApplication {
    Order order;
    DiscountPolicy discountPolicy;

    public BenefitApplication(Order order) {
        this.order = order;
        discountPolicy = new DiscountPolicy(order);
    }

    public void benefitApplication(){
        Benefits.setTotalAmountBeforeDiscount(Order.getTotalOrderAmount());

        if(Day.isBeforeDday(order.getVisitDate()) && isAvailableDiscount()){
            discountPolicy.dDayDiscount();
        }
        if(Day.isWeekday(order.getVisitDate()) && isAvailableDiscount()){
            discountPolicy.weekDayDiscount();
        }
        if(Day.isWeekend(order.getVisitDate()) && isAvailableDiscount()){
            discountPolicy.weekendDiscount();
        }
        if(Day.isSpecialDay(order.getVisitDate()) && isAvailableDiscount()){
            discountPolicy.specialDiscount();
        }
        if(Benefits.getTotalAmountBeforeDiscount()>=120000){
            discountPolicy.giftEvent();
        }
        if(isAvailableDiscount()){
            Benefits.accountTotalBenefit();
            Benefits.accountTotalAmountAfterDiscount();
            discountPolicy.getDecemberEventBadge();
        }
    }

    private boolean isAvailableDiscount(){
        return Benefits.getTotalAmountBeforeDiscount() >= 10000;
    }

}