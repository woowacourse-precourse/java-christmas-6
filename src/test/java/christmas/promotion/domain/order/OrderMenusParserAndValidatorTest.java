package christmas.promotion.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class OrderMenusParserAndValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "타파스-1,제로콜라-1",
            "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1",
            "내맘대로만든음식-123",
            ":타파스-1", // 입력 검증에서 예외를 뱉지 않지만, 음식이 ':타파스'가 메뉴판에 없으므로 Order 검증에서 예외를 뱉음
    })
    @DisplayName("주문 메뉴 입력 정상 케이스")
    void parse_정상(String input) {
        Map<String, Integer> result = OrderMenusParser.parse(input);
        assertThat(result.size()).isEqualTo(input.split(",").length);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "    타파스-1,제로콜라-1",
            "타파스-1,제로콜라-1 ",
            "타파스-1, 제로콜라-1",
            "타파스-1:제로콜라-1",
            "타파스1,제로콜라-1",
            "타파스",
            "타파스-1,",
            ",타파스-1"
    })
    @DisplayName("주문 메뉴 입력 예외 케이스")
    void parse_예외(String input) {
        assertThatThrownBy(() -> OrderMenusParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
