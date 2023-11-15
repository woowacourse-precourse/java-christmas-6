package christmas.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlannerControllerTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\r", "A"})
    void 날짜_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "32"})
    void 날짜_범위_입력_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "티본스테이크,1", "티본스테이크-1.바비큐립-1"
            , "티본스테이크=1,바비큐립=1", "바비큐립-1,바비큐립-3", "아이스티-2"})
    void 주문_입력_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException("5", input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"레드와인-1", "레드와인-1,제로콜라-2"})
    void 음료만_입력_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException("5", input);
            assertThat(output()).contains("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_메뉴_정상_출력() {
        assertSimpleTest(() -> {
            run("5", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "티본스테이크 1개",
                    "바비큐립 1개",
                    "초코케이크 2개",
                    "제로콜라 1개"
            );
        });
    }

    @Test
    void 기준_금액_이하_출력_테스트() {
        assertSimpleTest(() -> {
            run("5", "아이스크림-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 증정_이하_출력_테스트() {
        assertSimpleTest(() -> {
            run("5", "아이스크림-1,제로콜라-2");
            assertThat(output()).contains(
                    "<혜택 내역>" + LINE_SEPARATOR + "크리스마스 디데이 할인",
                    "<12월 이벤트 배지>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 샴페인_증정_출력_테스트() {
        assertSimpleTest(() -> {
            run("5", "티본스테이크-5,제로콜라-10");
            assertThat(output()).contains(
                    "<혜택 내역>" + LINE_SEPARATOR + "증정 이벤트: -25,000원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개",
                    "<12월 이벤트 배지>" + LINE_SEPARATOR + "산타");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}