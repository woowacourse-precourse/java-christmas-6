package christmas.domain;

import christmas.utils.Validator;

public class Customer {
    private int visitedDate;
    private Discount discount = new Discount();

    public Customer(int visitedDate) {
        validate(visitedDate);
        this.visitedDate = visitedDate;
    }

    public int getVisitedDate() {
        return visitedDate;
    }

    private void validate(int visitedDate) {
        Validator.checkDateValid(visitedDate);
    }

    public void setChristmasDiscountPrice(int money) {
        discount.setChristmasDiscount(money);
    }

}
