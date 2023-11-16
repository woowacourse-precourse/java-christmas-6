package christmas.promotion.domain.event.manager;

import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.domain.order.EventfulOrder;
import christmas.promotion.domain.order.Order;
import christmas.promotion.domain.order.OrderMenusParser;
import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.dto.EventfulOrderDto;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EventManagerTest {

    EventManager eventManager;
    MenuBoard menuBoard;
    Order order;

    @BeforeEach
    void beforeEach() {
        menuBoard = new MenuBoard();
    }

    /**
     * @param date                 날짜
     * @param menuDetails          주문 메뉴들
     * @param giftMenusString      증정 메뉴
     * @param benefitPrice         총 혜택 금액
     * @param discountedFinalPrice 할인 후 예상 결제 금액
     * @param badgeName            배지 이름
     */
    @ParameterizedTest
    @CsvSource(value = {
            "2023-12-03 : 타파스-1 : 없음 : 0 : 5500 : 없음",
            "2023-12-25 : 초코케이크-1,샴페인-3 : 없음 : 6423 : 83577 : 별 ",
            "2023-12-26 : 아이스크림-20 : 없음 : 40460 : 59540 : 산타",
            "2023-12-30 : 제로콜라-5,양송이수프-2,티본스테이크-5,해산물파스타-2,바비큐립-2 : 샴페인 : 43207 : 461793 : 산타"
    }, delimiter = ':')
    void applyEvents(LocalDate date, String menuDetails, String giftMenusString, double benefitPrice,
                     double discountedFinalPrice, String badgeName) {

        Map<String, Integer> orderMenus = OrderMenusParser.parse(menuDetails);
        order = new Order(orderMenus, new DecemberVisitDate(date), menuBoard);
        eventManager = new EventManager(order);

        eventManager.applyEvents();
        EventfulOrder eventfulOrder = eventManager.createEventfulOrder();
        EventfulOrderDto eventfulOrderDto = eventfulOrder.toEventfulOrderDto();

        /**
         * 증정 메뉴
         * 현재 증정 메뉴는 1개지만, 증정 메뉴가 여러 개가 될 수 있음. (이를 대비해 테스트 할 때 ','를 구분자로 짜른다)
         */
        String[] exceptedGiftMenuNames = giftMenusString.split(",");
        int pos = 0;
        Map<Menu, Quantity> actualGiftMenus = eventfulOrderDto.getGiftMenus().giftMenus();
        for (Menu menu : actualGiftMenus.keySet()) {
            assertThat(menu.getName()).isEqualTo(exceptedGiftMenuNames[pos++]);
        }

        // 총 혜택 금액
        Price actualTotalBenefitPrice = eventfulOrderDto.getTotalBenefitPrice();
        assertThat(-1 * actualTotalBenefitPrice.price()).isEqualTo(benefitPrice);

        // 할인 후 예상 결제 금액
        Price actualDiscountedPrice = eventfulOrderDto.getDiscountedFinalPrice();
        assertThat(actualDiscountedPrice.price()).isEqualTo(discountedFinalPrice);

        // 배지
        Badge actualBadge = eventfulOrderDto.getBadge();
        assertThat(actualBadge.getName()).isEqualTo(badgeName);
    }

}