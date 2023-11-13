package christmas.service;

import christmas.domain.Customer;

public class PlannerService {
    private Customer customer;
    public Customer setVisitedDate(int date) {
        customer = new Customer(date);
        return customer;
    }
}
