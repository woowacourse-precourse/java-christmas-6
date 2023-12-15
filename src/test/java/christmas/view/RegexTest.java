package christmas.view;

import java.util.regex.Pattern;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RegexTest {
    Pattern pattern = InputView.MENU_PATTERN;

    @Test
    @DisplayName("메뉴 입력 정규식 성공 테스트")
    void 메뉴_입력_정규식_성공_테스트() {
        String menu = "메뉴-1";
        Assertions.assertThat(pattern.matcher(menu).matches())
                .isTrue();
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"메뉴1", "메뉴,1", "메뉴-1-1"})
    @DisplayName("입력 패턴에 맞지 않는 입력일 때 정규식 실패")
    void 메뉴_입력_정규식_실패_테스트(String menu) {
        Assertions.assertThat(pattern.matcher(menu).matches())
                .isFalse();
    }

    @Test
    @DisplayName("개수 위치에 숫자가 아닌 입력시 정규식 실패")
    void 메뉴_입력_정규식_실패_테스트2() {
        String menu = "메뉴-ㄱ";
        Assertions.assertThat(pattern.matcher(menu).matches())
                .isFalse();
    }

    @Test
    @DisplayName("메뉴 이름이 비어있으면 정규식 실패")
    void 메뉴_입력_정규식_실패_테스트3() {
        String menu = "-1";
        Assertions.assertThat(pattern.matcher(menu).matches())
                .isFalse();
    }

    @Test
    @DisplayName("메뉴 이름이 한글이 아니면 정규식 실패")
    void 메뉴_입력_정규식_실패_테스트4() {
        String menu = "menu-1";
        Assertions.assertThat(pattern.matcher(menu).matches())
                .isFalse();
    }
}