package christmas.domain;

public class Customer {
    private int visitedDate;

    public Customer(int visitedDate) {
        validate(visitedDate);
        this.visitedDate = visitedDate;
    }

    private void validate(int visitedDate) {
    }
}
