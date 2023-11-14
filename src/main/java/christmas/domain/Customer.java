package christmas.domain;

import christmas.utils.Validator;

public class Customer {
    private int visitedDate;
    private boolean isGiven = false;
    private Discount discount = new Discount();

    public Customer(int visitedDate) {
        validate(visitedDate);
        this.visitedDate = visitedDate;
    }

    public int getVisitedDate() {
        return visitedDate;
    }

    public int getTotalDiscountPrice() {
        return discount.getChristmasDiscount() + discount.getNormalDiscount()
                + discount.getSpecialDiscount() + discount.getGivingEventDiscount();
    }

    private void validate(int visitedDate) {
        Validator.checkDateValid(visitedDate);
    }

    public void setChristmasDiscountPrice(int money) {
        discount.setChristmasDiscount(money);
    }

    public void setSpecialDiscountPrice(int money) {
        discount.setSpecialDiscount(money);
    }

    public void setNormalDiscountPrice(int money) {
        discount.setNormalDiscount(money);
    }

    public void setWeekDiscountPrice(int money) {
        discount.setWeekDiscount(money);
    }

    public void setGivingEventDiscount(int price) {
        discount.setGivingEventDiscount(price);
    }

    public Discount getDiscount() {
        return discount;
    }
    public void changeIsGiven() {
        isGiven = !isGiven;
    }

    public boolean isGiven() {
        return isGiven;
    }
}
