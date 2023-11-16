package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.exception.OrderMenuException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class OrderTest {

    /**
     * 메뉴판
     * <p>
     * <애피타이저>
     * 양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)
     * <메인>
     * 티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)
     * <디저트>
     * 초코케이크(15,000), 아이스크림(5,000)
     * <음료>
     * 제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
     */

    MenuBoard menuBoard;
    LocalDate date;

    @BeforeEach
    void beforeEach() {
        menuBoard = new MenuBoard();
        date = LocalDate.of(2023, 12, 3);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "타파스-1 : 1 : 5500",
            "초코케이크-1,샴페인-3 : 4 : 90000",
            "아이스크림-20 : 20 : 100000",
            "제로콜라-5,티본스테이크-5,해산물파스타-2,양송이수프-2,바비큐립-2 : 16 : 480000"
    }, delimiter = ':')
    @DisplayName("정상 주문, 음료만 주문 x, 존재하는 메뉴 주문, 메뉴 개수는 1개 이상, 메뉴 총 개수는 20개 이하")
    void 정상_주문(String orderMenuDetails, int orderSize, int exceptedTotalPrice) {
        Map<String, Integer> orderMenus = OrderMenusParser.parse(orderMenuDetails);
        Order order = new Order(orderMenus, new DecemberVisitDate(date), menuBoard);

        double totalPrice = 0;
        int totalQuantity = 0;
        for (OrderMenu menu : order.getOrderMenus()) {
            totalQuantity += menu.getQuantity().quantity();
            totalPrice += menu.getMenu().getPrice() * menu.getQuantity().quantity();
        }

        assertThat(totalQuantity).isEqualTo(orderSize);
        assertThat(totalPrice).isEqualTo(exceptedTotalPrice);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "제로콜라, 레드와인",
            "제로콜라, 샴페인",
            "레드와인, 샴페인",
    })
    @DisplayName("주문이 전부 음료인 경우, 예외")
    void 주문이_전부_음료인_경우_예외(String input1, String input2) {
        Map<String, Integer> orderMenus = new LinkedHashMap<>();
        orderMenus.put(input1, 1);
        orderMenus.put(input2, 1);

        assertThatThrownBy(() -> new Order(orderMenus, new DecemberVisitDate(date), menuBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(OrderMenuException.class)
                .hasMessageContaining(OrderMenuException.ErrorMessage.ORDER_MENU_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "타파스-19,해산물파스타-2",
            "제로콜라-10,티본스테이크-2,해산물파스타-3,양송이수프-3,바비큐립-5",
            "타파스-1,시저샐러드-10,아이스크림-9,초코케이크-1",
            "초코케이크-101"
    })
    @DisplayName("메뉴 주문 개수가 20개가 넘는 경우, 예외")
    void orderCreation_InvalidMaxSize_ShouldThrowException(String orderDetails) {
        Map<String, Integer> orderMenus = OrderMenusParser.parse(orderDetails);
        assertThatThrownBy(() -> new Order(orderMenus, new DecemberVisitDate(date), menuBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(OrderMenuException.class)
                .hasMessageContaining(OrderMenuException.ErrorMessage.ORDER_MENU_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "타파스-19,해산물파스타-2",
            "제로콜라-10,티본스테이크-2,해산물파스타-3,양송이수프-3,바비큐립-5",
            "타파스-1,시저샐러드-10,아이스크림-9,초코케이크-1",
            "초코케이크-101"
    })
    @DisplayName("메뉴 주문 개수가 20개가 넘는 경우, 예외")
    void 주문에_중복_메뉴가_포함된_경우_예외(String orderDetails) {
        Map<String, Integer> orderMenus = OrderMenusParser.parse(orderDetails);
        assertThatThrownBy(() -> new Order(orderMenus, new DecemberVisitDate(date), menuBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(OrderMenuException.class)
                .hasMessageContaining(OrderMenuException.ErrorMessage.ORDER_MENU_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "존재하지않는메뉴-1",
            "타스파-1,아이스크림-3,존재하지않는메뉴-1"
    })
    @DisplayName("존재하지 않는 메뉴를 주문한 경우, 예외")
    void 존재하지_않는_메뉴를_주문한_경우_예외(String orderMenuDetails) {
        Map<String, Integer> orderMenus = OrderMenusParser.parse(orderMenuDetails);
        assertThatThrownBy(() -> new Order(orderMenus, new DecemberVisitDate(date), menuBoard))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(OrderMenuException.class)
                .hasMessageContaining(OrderMenuException.ErrorMessage.ORDER_MENU_ERROR.getMessage());
    }
}