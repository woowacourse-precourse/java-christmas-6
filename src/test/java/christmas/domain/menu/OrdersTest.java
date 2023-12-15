package christmas.domain.menu;

import static org.junit.jupiter.api.Assertions.*;

import christmas.exception.PromotionExceptionMaker;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OrdersTest {

    @Nested
    @DisplayName("생성 테스트")
    class CreateTest {

        @Test
        @DisplayName("정상 생성 테스트")
        void create() {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> new Orders(List.of(
                            new MenuAndCount(Menu.CAESAR_SALAD, 1),
                            new MenuAndCount(Menu.BARBECUE_RIBS, 1),
                            new MenuAndCount(Menu.CHOCO_CAKE, 2)
                    )));
        }

        @Test
        @DisplayName("from 생성 테스트")
        void from() {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> Orders.from(List.of(
                            "티본스테이크-1",
                            "바비큐립-1",
                            "초코케이크-2",
                            "제로콜라-1"
                    )));
        }

        @Test
        @DisplayName("중복 주문 테스트")
        void create_duplicate() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> Orders.from(List.of(
                            "제로콜라-1",
                            "제로콜라-1",
                            "티본스테이크-1"
                    )))
                    .withMessage(PromotionExceptionMaker.DUPLICATE_ORDER.getMessage());
        }

        @Test
        @DisplayName("음료만 주문 테스트")
        void create_onlyDrink() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> Orders.from(List.of(
                            "제로콜라-1",
                            "레드와인-1",
                            "샴페인-2"
                    )))
                    .withMessage(PromotionExceptionMaker.ALL_ORDER_DRINK.getMessage());
        }

        @Test
        @DisplayName("주문 수량 20개 초과 테스트")
        void create_tooMany() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> Orders.from(List.of(
                            "제로콜라-2",
                            "레드와인-1",
                            "타파스-18"
                    )))
                    .withMessage(PromotionExceptionMaker.TOO_MANY_ORDERS.getMessage());
        }
    }
}