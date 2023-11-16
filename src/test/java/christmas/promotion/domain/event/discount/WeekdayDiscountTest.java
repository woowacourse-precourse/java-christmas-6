package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.LocalEvent;
import christmas.promotion.domain.menu.EventfulMenu;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;
import christmas.promotion.domain.visitdate.DecemberVisitDate;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayDiscountTest {

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);

    LocalEvent weekdayDiscount = WeekdayDiscount.INSTANCE;

    @Test
    @DisplayName("이벤트 이름 확인")
    void eventName() {
        assertThat(weekdayDiscount.getEventName()).isEqualTo("평일 할인");
    }

    @ParameterizedTest
    @CsvSource({
            "2023-11-12, 초코케이크, 1, 0", // 11월
            "2023-12-07, 초코케이크, 1, 2023",
            "2023-12-10, 초코케이크, 3, 6069",
            "2023-12-15, 초코케이크, 1, 0", // 금요일
            "2023-12-16, 초코케이크, 1, 0", // 토요일
            "2023-12-16, 샴페인, 3, 0",    // 디저트 아님
            "2024-12-25, 초코케이크, 1, 0" // 2024년
    })
    @DisplayName("평일 할인 적용 테스트, 2023-12-1 ~ 2023-12-31 & 일 ~ 목 & 디저트 2023원 할인")
    void applyDiscount(LocalDate date, String menuName, int quantity, double excepted) {
        MenuBoard menuBoard = new MenuBoard();
        EventfulMenu eventfulMenu = menuBoard.findMenu(menuName);

        // 디저트 인 경우에만 작동
        Map<Event, Double> actual = eventfulMenu.applyEvent(new DecemberVisitDate(date), new Quantity(quantity));
        double total = 0;
        for (Double price : actual.values()) {
            total += price;
        }

        Price actualPrice = Price.of(total);
        assertThat(actualPrice.price()).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-11-30, false",
            "2024-12-10, false",
            "2023-12-10, true",
            "2023-12-25, true",
            "2023-12-26, true",
    })
    @DisplayName("날짜에 대한 이벤트 가능 판단 테스트,2023-12-1 ~ 2023-12-31 & 평일만 적용")
    void isPossibleEvnet_날짜(LocalDate date, boolean expected) {
        assertThat(weekdayDiscount.isPossibleEvent(new DecemberVisitDate(date))).isEqualTo(expected);
    }

    /**
     * 로컬 이벤트에 대한 최소 금액 조건은, 총 금액이 1만원 이상인 경우 부터다.
     * 하지만 로컬 이벤트에는 이 최소 금액을 검증하는 조건을 안만들었다. 사실 글로벌 이벤트 조건에도 필요없다.
     * 왜냐하면, 이벤트 매니저에서 모든 이벤트를 적용하기전에 주문 금액이 10000원이 넘는지 검증한다.
     * 하지만 글로벌 이벤트에만 만들어 둔 이유는, 샴페인 기프트 이벤트 처럼 원하는 최소 금액 등에 대한 검증이 필요할 수 있기 때문이다.
     * 그에 반면, 로컬 이벤트의 경우에는 eventfulMenu 즉, 각각의 메뉴마다 서로 다른 이벤트가 적용 되있고,
     * 로컬 이벤트를 적용할 수 있는지에 대한 검증을 이벤트 적용 전에 이벤트매니저가 한번에 하고 조건이 맞다면
     * 모든 메뉴에 대한 로컬 이벤트를 적용 시키기 때문이다.
     */
}
