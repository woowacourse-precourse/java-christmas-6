package christmas.service;

import christmas.domain.Customer;
import christmas.domain.Order;
import christmas.type.MenuType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PlannerServiceTest {
    private Customer customer;
    private Order order = new Order();
    private PlannerService plannerService = new PlannerService();

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 32})
    void 날짜_범위_예외_테스트(int date) {
        //when && then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> plannerService.setVisitedDate(date)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    void 날짜_정상_범위_테스트(int date) {
        // when
        Customer exCustomer = plannerService.setVisitedDate(date);

        // then
        Assertions.assertEquals(date, exCustomer.getVisitedDate());
    }


}