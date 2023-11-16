package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.promotion.repository.EventApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * EventApplicationRepository, 정적 변수 초기화
     */
    @BeforeEach
    void beforeEach() throws NoSuchFieldException, IllegalAccessException {
        Field eventfulMenuSalePrice = EventApplicationRepository.class.getDeclaredField("eventfulMenuSalePrice");
        eventfulMenuSalePrice.setAccessible(true);
        eventfulMenuSalePrice.set(EventApplicationRepository.INSTANCE, new DoubleAdder());

        Field eventParticipationCount = EventApplicationRepository.class.getDeclaredField("eventParticipationCount");
        eventParticipationCount.setAccessible(true);
        eventParticipationCount.set(EventApplicationRepository.INSTANCE, new AtomicInteger(0));
    }

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 모든_내용_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(EventApplicationRepository.INSTANCE.getSalePrice()).isEqualTo(135754);
            assertThat(EventApplicationRepository.INSTANCE.getEventParticipationCount()).isEqualTo(1);
            assertThat(output()).contains(
                    "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.",
                    "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)",
                    "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)",
                    "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                    "<주문 메뉴>",
                    """
                            티본스테이크 1개
                            바비큐립 1개
                            초코케이크 2개
                            제로콜라 1개""",
                    "<할인 전 총주문 금액>",
                    "142,000원",
                    "<증정 메뉴>",
                    "샴페인 1개",
                    "<혜택 내역>",
                    """
                            크리스마스 디데이 할인: -1,200원
                            평일 할인: -4,046원
                            특별 할인: -1,000원
                            증정 이벤트: -25,000원""",
                    "<총혜택 금액>",
                    "-31,246원",
                    "<할인 후 예상 결제 금액>",
                    "135,754원",
                    "<12월 이벤트 배지>",
                    "산타"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
            assertThat(output()).doesNotContain("샴페인 1개");
        });
    }

    @Test
    void 데이터베이스_테스트() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(EventApplicationRepository.INSTANCE.getSalePrice()).isEqualTo(135_754.0);
            assertThat(EventApplicationRepository.INSTANCE.getEventParticipationCount()).isEqualTo(1);
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}