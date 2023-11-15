package christmas.service;

import christmas.domain.Customer;
import christmas.domain.Order;
import christmas.type.MenuType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlannerServiceTest {
    private Customer customer;
    private Order order = new Order();
    private PlannerService plannerService = new PlannerService(order);

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

    @ParameterizedTest
    @CsvSource(value = {"봉골레파스타, 1", "해산물파스타, 21", "제로콜라, 3"})
    // 없는 메뉴, 갯수 초과, 음료만 주문
    void 주문_예외_테스트(String menu, int count) {
        // given
        HashMap<String, Integer> menuMap = new HashMap<>();
        menuMap.put(menu, count);

        // when && then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> plannerService.setOrder(menuMap)
        );
    }

    @Test
    void 정상_음식_주문_테스트() {
        // given
        HashMap<String, Integer> menuMap = new HashMap<>();
        menuMap.put(MenuType.CHRIST_MAS_PASTA.getFoodName(), 1);

        // when
        plannerService.setOrder(menuMap);
        HashMap<String, Integer> orderMap = plannerService.getOrder();

        //then
        Assertions.assertEquals(1, order.getTotalOrder().size());
        Assertions.assertEquals(1, orderMap.get(MenuType.CHRIST_MAS_PASTA.getFoodName()));
    }

    @ParameterizedTest
    @CsvSource(value = {"10, 4923, 1", "7, 3623, 1", "6, 64937, 19"})
    void 평일_날짜_할인_금액_테스트(int date, int value, int count) {
        // given
        HashMap<String, Integer> menuMap = new HashMap<>();
        menuMap.put(MenuType.CHRIST_MAS_PASTA.getFoodName(), 1);
        menuMap.put(MenuType.ICE_CREAM.getFoodName(), count);
        plannerService.setVisitedDate(date);
        plannerService.setOrder(menuMap);

        // when
        plannerService.calculateDiscount(date);
        int totalBenefit = plannerService.getTotalBenefit();

        //then
        Assertions.assertEquals(value, totalBenefit);
    }

    @ParameterizedTest
    @CsvSource(value = {"9, 3823, 1", "16, 4523, 1", "23, 38315, 5"})
    void 주말_날짜_할인_금액_테스트(int date, int value, int count) {
        // given
        HashMap<String, Integer> menuMap = new HashMap<>();
        menuMap.put(MenuType.CHRIST_MAS_PASTA.getFoodName(), count);
        menuMap.put(MenuType.ICE_CREAM.getFoodName(), 1);
        plannerService.setVisitedDate(date);
        plannerService.setOrder(menuMap);

        // when
        plannerService.calculateDiscount(date);
        int totalBenefit = plannerService.getTotalBenefit();

        //then
        Assertions.assertEquals(value, totalBenefit);
    }

}