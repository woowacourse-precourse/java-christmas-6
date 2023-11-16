package christmas.promotion.domain.menu;

import christmas.promotion.exception.OrderMenuException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MenuBoardTest {

    private MenuBoard menuBoard;

    @BeforeEach
    void beforeEach() {
        menuBoard = new MenuBoard();
    }

    @Test
    @DisplayName("instanceof 테스트")
    void 정상_케이스() {
        for (EventfulMenu eventfulMenu : menuBoard.getMenuBoard()) {
            if (eventfulMenu.getMenu() instanceof Beverage) {
                System.out.println("eventfulMenu.getMenu() " + eventfulMenu.getName());
            }
            if (eventfulMenu.getMenu().getClass() == Beverage.class) {
                System.out.println("eventfulMenu.getMenu().getClass()  " + eventfulMenu.getName());
            }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "양송이수프",
            "타파스",
            "시저샐러드",
            "티본스테이크",
            "바비큐립",
            "해산물파스타",
            "크리스마스파스타",
            "초코케이크",
            "아이스크림",
            "제로콜라",
            "레드와인",
            "샴페인"
    })
    @DisplayName("존재하는 메뉴 이름으로 찾기, 성공")
    void 존재하는_메뉴_찾기(String input) {
        EventfulMenu menu = menuBoard.findMenu(input);
        assertThat(input).isNotNull();
        assertThat(menu.getName()).isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "notFound",
            "햄버거",
            "a",
            "  ",
            " 제로콜라",
            "제로콜라 "

    })
    @DisplayName("존재하지 않는 메뉴 이름으로 찾기, 예외")
    void 존재하지_않는_메뉴_찾기(String input) {
        assertThatThrownBy(() -> menuBoard.findMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(OrderMenuException.class)
                .hasMessageContaining(OrderMenuException.ErrorMessage.ORDER_MENU_ERROR.getMessage());
    }
}