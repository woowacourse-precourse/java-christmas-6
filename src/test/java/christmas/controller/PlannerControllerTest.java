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


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}