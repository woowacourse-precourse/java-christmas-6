package christmas.systems;

import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKEND_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import christmas.enums.events.decemberevent.GiftEvents;
import christmas.systems.eventSystem.EventInitializer;
import christmas.systems.eventSystem.EventSystem;
import christmas.systems.ordersystem.OrderSystem;
import christmas.systems.reservationsystem.ReservationProcessor;
import christmas.systems.reservationsystem.ReservationSystem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReservationProcessorTest {
    private final static String RESTAURANT_NAME = "우테코 식당";
    private final static String ILLEGAL_DATE_FORMAT_EXCEPTION = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private final static String ILLEGAL_ONLY_BEVERAGE_EXCEPTION = "죄송합니다. 음료만 주문하실 수는 없습니다. 다른 메뉴도 함께 주문하시는것은 어떠실까요?";
    private final static String ILLEGAL_ORDER_FORMAT_EXCEPTION = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private final static String ILLEGAL_OUT_OF_NUMBER_MENU_EXCEPTION = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n"
            + "(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        Console.close();
    }

    ReservationProcessor setALLEvent() {
        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(CHRISTMAS_D_DAY_DISCOUNT.getInstance());
        eventInitializer.specialDiscountEventAdd(SPECIAL_DISCOUNT.getInstance());
        eventInitializer.amountToGiftEventsAdd(GiftEvents.GIFT_EVENT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKDAY_DISCOUNT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKEND_DISCOUNT.getInstance());

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        ReservationSystem reservationSystem = new ReservationSystem(orderSystem);
        return new ReservationProcessor(reservationSystem);
    }

    ReservationProcessor setOneEvent() {
        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.specialDiscountEventAdd(SPECIAL_DISCOUNT.getInstance());

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        ReservationSystem reservationSystem = new ReservationSystem(orderSystem);
        return new ReservationProcessor(reservationSystem);
    }

    ReservationProcessor setNoEvent() {
        EventInitializer eventInitializer = new EventInitializer();

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        ReservationSystem reservationSystem = new ReservationSystem(orderSystem);
        return new ReservationProcessor(reservationSystem);
    }

    @DisplayName("모든 이벤트가 등록되었을 때 정상적인 동작을 검증한다.")
    @Test
    void processWithAllEvent() {
        final String input = "3\n티본스테이크-1,레드와인-1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ReservationProcessor reservationProcessor = this.setALLEvent();
        reservationProcessor.process(RESTAURANT_NAME, 2023, 12);
        String output = outContent.toString();
        assertThat(output).contains("<주문 메뉴>", "티본스테이크 1개", "레드와인 1개");
        assertThat(output).contains("<할인 전 총주문 금액>", "115,000원");
        assertThat(output).contains("<혜택 내역>", "크리스마스 디데이 할인 -1,200원", "특별 할인 -1,000원", "평일 할인 -2,023원");
        assertThat(output).contains("<할인 후 예상 결제 금액>", "110,777원");
        assertThat(output).contains("<12월 이벤트 배지>", "없음");
    }

    @DisplayName("등록된 이벤트가 1개일 때 정상적인 동작을 검증한다.")
    @Test
    void processWithOneEvent() {
        final String input = "3\n티본스테이크-1,레드와인-1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ReservationProcessor reservationProcessor = this.setOneEvent();
        reservationProcessor.process(RESTAURANT_NAME, 2023, 12);
        String output = outContent.toString();
        assertThat(output).contains("<주문 메뉴>", "티본스테이크 1개", "레드와인 1개");
        assertThat(output).contains("<할인 전 총주문 금액>", "115,000원");
        assertThat(output).contains("<혜택 내역>", "특별 할인 -1,000원");
        assertThat(output).contains("<할인 후 예상 결제 금액>", "114,000원");
        assertThat(output).contains("<12월 이벤트 배지>", "없음");
    }

    @DisplayName("등록된 이벤트가 없을 때 정상적인 동작을 검증한다.")
    @Test
    void processWithNoEvent() {
        final String input = "3\n티본스테이크-1,레드와인-1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ReservationProcessor reservationProcessor = this.setNoEvent();
        reservationProcessor.process(RESTAURANT_NAME, 2023, 12);
        String output = outContent.toString();
        assertThat(output).contains("<주문 메뉴>", "티본스테이크 1개", "레드와인 1개");
        assertThat(output).contains("<할인 전 총주문 금액>", "115,000원");
        assertThat(output).contains("<혜택 내역>", "없음");
        assertThat(output).contains("<할인 후 예상 결제 금액>", "115,000원");
        assertThat(output).contains("<12월 이벤트 배지>", "없음");
    }


    @ParameterizedTest
    @MethodSource("provideInputForMessageTesting")
    @DisplayName("다양한 입력에 대한  메시지 출력 검증")
    void testInputMessages(String input, String outputMessage) {
        //given
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ReservationProcessor reservationProcessor = this.setALLEvent();

        //when
        reservationProcessor.process(RESTAURANT_NAME, 2023, 12);
        String output = outContent.toString();

        //then
        assertThat(output).contains(outputMessage);
    }

    static Stream<Arguments> provideInputForMessageTesting() {
        return Stream.of(
                Arguments.of("3\n아이스크림-1\n", "<12월 이벤트 배지>" + System.lineSeparator() + "없음"),
                Arguments.of("3\n아이스크림-1\n", "<혜택 내역>" + System.lineSeparator() + "없음"),
                Arguments.of("3\n아이스크림-1\n", "<할인 후 예상 결제 금액>" + System.lineSeparator() + "5,000원"),
                Arguments.of("25\n아이스크림-1\n", "<12월 이벤트 배지>" + System.lineSeparator() + "없음"),
                Arguments.of("25\n아이스크림-1\n", "<혜택 내역>" + System.lineSeparator() + "없음"),
                Arguments.of("25\n아이스크림-1\n", "<할인 후 예상 결제 금액>" + System.lineSeparator() + "5,000원"),
                Arguments.of("3\n티본스테이크-1,레드와인-1\n", "<총혜택 금액>" + System.lineSeparator() + "-4,223원"),
                Arguments.of("3\n티본스테이크-1,레드와인-1\n", "<12월 이벤트 배지>" + System.lineSeparator() + "없음"),
                Arguments.of("25\n티본스테이크-1,레드와인-1\n", "<총혜택 금액>" + System.lineSeparator() + "-6,423원"),
                Arguments.of("25\n티본스테이크-1,레드와인-1\n", "<12월 이벤트 배지>" + System.lineSeparator() + "별"),
                Arguments.of("25\n크리스마스파스타-3\n", "<총혜택 금액>" + System.lineSeparator() + "-10,469원"),
                Arguments.of("25\n크리스마스파스타-3\n", "<12월 이벤트 배지>" + System.lineSeparator() + "트리"),
                Arguments.of("25\n티본스테이크-2,레드와인-1\n", "<총혜택 금액>" + System.lineSeparator() + "-33,446원"),
                Arguments.of("25\n티본스테이크-2,레드와인-1\n", "<12월 이벤트 배지>" + System.lineSeparator() + "산타"),
                Arguments.of("31\n티본스테이크-2,레드와인-1\n", "<총혜택 금액>" + System.lineSeparator() + "-30,046원"),
                Arguments.of("31\n티본스테이크-2,레드와인-1\n", "<12월 이벤트 배지>" + System.lineSeparator() + "산타")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputForExceptionTesting")
    @DisplayName("다양한 입력에 대한 예외 메시지 출력 검증")
    void testInputExceptionMessages(String input, String expectedExceptionMessage) {
        //given
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ReservationProcessor reservationProcessor = this.setALLEvent();

        //when
        reservationProcessor.process(RESTAURANT_NAME, 2023, 12);
        String output = outContent.toString();

        //then
        assertThat(output).contains(expectedExceptionMessage);
    }

    static Stream<Arguments> provideInputForExceptionTesting() {
        return Stream.of(
                Arguments.of("s\n3\n티본스테이크-1,레드와인-1\n", ILLEGAL_DATE_FORMAT_EXCEPTION),
                Arguments.of(" \n3\n티본스테이크-1,레드와인-1\n", ILLEGAL_DATE_FORMAT_EXCEPTION),
                Arguments.of("\n3\n티본스테이크-1,레드와인-1\n", ILLEGAL_DATE_FORMAT_EXCEPTION),
                Arguments.of("!\n3\n티본스테이크-1,레드와인-1\n", ILLEGAL_DATE_FORMAT_EXCEPTION),
                Arguments.of("0\n3\n티본스테이크-1,레드와인-1\n", ILLEGAL_DATE_FORMAT_EXCEPTION),
                Arguments.of("33\n3\n티본스테이크-1,레드와인-1\n", ILLEGAL_DATE_FORMAT_EXCEPTION),
                Arguments.of("3\n레드와인-1\n티본스테이크-1,레드와인-1\n", ILLEGAL_ONLY_BEVERAGE_EXCEPTION),
                Arguments.of("3\n티본스테이크-s,레드와인-1\n티본스테이크-1,레드와인-1\n", ILLEGAL_ORDER_FORMAT_EXCEPTION),
                Arguments.of("3\n티본스테이크-0,레드와인-1\n티본스테이크-1,레드와인-1\n", ILLEGAL_ORDER_FORMAT_EXCEPTION),
                Arguments.of("3\n티본스테이크-1,티본스테이크-1\n티본스테이크-1,레드와인-1\n", ILLEGAL_ORDER_FORMAT_EXCEPTION),
                Arguments.of("3\n티본스테이크-20,레드와인-1\n티본스테이크-1,레드와인-1\n", ILLEGAL_OUT_OF_NUMBER_MENU_EXCEPTION)
        );
    }
}