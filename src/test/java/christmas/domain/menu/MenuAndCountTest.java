package christmas.domain.menu;

import static org.junit.jupiter.api.Assertions.*;

import christmas.exception.PromotionExceptionMaker;
import java.util.Arrays;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    @Nested
    @DisplayName("카테고리 확인 테스트")
    class CategoryTest{

        @ParameterizedTest
        @MethodSource("drinkMenus")
        @DisplayName("")
        void 음료_카테고리_확인(Menu menu){
            // given
            MenuAndCount menuAndCount = new MenuAndCount(menu, 1);

            // when
            Assertions.assertThat(menuAndCount.isCategory(Category.BEVERAGE))
                    .isTrue();
            Arrays.stream(Category.values())
                    .filter(category -> category != Category.BEVERAGE)
                    .forEach(category ->
                            Assertions.assertThat(menuAndCount.isCategory(category))
                                    .isFalse()
                    );
        }

        static Stream<Menu> drinkMenus(){
            return Stream.of(
                Menu.ZERO_COLA,
                Menu.RED_WINE,
                Menu.CHAMPAGNE
            );
        }

        @ParameterizedTest
        @MethodSource("MainMenus")
        @DisplayName("")
        void 메인_카테고리_확인(Menu menu){
            // given
            MenuAndCount menuAndCount = new MenuAndCount(menu, 1);

            // when
            Assertions.assertThat(menuAndCount.isCategory(Category.MAIN_COURSE))
                    .isTrue();
            Arrays.stream(Category.values())
                    .filter(category -> category != Category.MAIN_COURSE)
                    .forEach(category ->
                            Assertions.assertThat(menuAndCount.isCategory(category))
                                    .isFalse()
                    );
        }

        static Stream<Menu> MainMenus(){
            return Stream.of(
                Menu.T_BONE_STEAK,
                Menu.BARBECUE_RIBS,
                Menu.SEAFOOD_PASTA,
                Menu.CHRISTMAS_PASTA
            );
        }

    }
}