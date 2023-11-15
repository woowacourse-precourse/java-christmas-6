package christmas.domain.services;

import christmas.domain.entity.Benefits;
import christmas.domain.entity.Order;
import christmas.domain.generators.OrderGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitServiceTest {

    @DisplayName("주문 메뉴와 날짜에 따라 할인혜택을 적용한다.")
    @Test
    void returnBenefitTest(){
        //Assert
        Order.emptyMap();
        OrderGenerator orderGenerator = new OrderGenerator();
        BenefitService benefitService = new BenefitService(
                orderGenerator.generateOrder("양송이수프-1,티본스테이크-2,아이스크림-2,레드와인-1", 8)
        );

        //Act
        benefitService.benefitApplication();

        //Assert
        assertThat(Benefits.getdDayDiscountAmount()).isEqualTo(1700);
        assertThat(Benefits.getWeekendDiscountAmount()).isEqualTo(4046);
        assertThat(Benefits.getGiftMenu()).isEqualTo("샴페인");
        assertThat(Benefits.getTotalBenefitAmount()).isEqualTo(30746);
        assertThat(Benefits.getDecemberEventBadge()).isEqualTo("산타");
    }
}
