package christmas.domain;

import christmas.valid.Validator;

public class Customer {
    private int visitedDate;

    public Customer(int visitedDate) {
        validate(visitedDate);
        this.visitedDate = visitedDate;
    }

    private void validate(int visitedDate) {
        Validator.checkDateValid(visitedDate);
    }
}
