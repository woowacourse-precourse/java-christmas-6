package christmas.domain.menu;

import static org.junit.jupiter.api.Assertions.*;

import christmas.exception.PromotionExceptionMaker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MenuAndCountTest {

    @Nested
    @DisplayName("생성 테스트")
    class CreateTest {

        @Test
        @DisplayName("정상 생성 테스트")
        void create() {
            // given
            Menu menu = Menu.CAESAR_SALAD;
            int count = 1;

            // when
            Assertions.assertThatNoException()
                    .isThrownBy(() -> new MenuAndCount(menu, count));
        }

        @Test
        @DisplayName("음수 주문 테스트")
        void create_negativeCount() {
            // given
            Menu menu = Menu.CAESAR_SALAD;
            int count = -1;

            // when
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new MenuAndCount(menu, count))
                    .withMessage(PromotionExceptionMaker.INVALID_COUNT.getMessage());
        }

        @Test
        @DisplayName("0개 주문 테스트")
        void create_zeroCount() {
            // given
            Menu menu = Menu.CAESAR_SALAD;
            int count = 0;

            // when
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new MenuAndCount(menu, count))
                    .withMessage(PromotionExceptionMaker.INVALID_COUNT.getMessage());
        }
    }
}