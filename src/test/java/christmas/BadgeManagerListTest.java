package christmas;

import static christmas.enums.badge.benefit.BenefitBadge.NONE;
import static christmas.enums.badge.benefit.BenefitBadge.SANTA;
import static christmas.enums.badge.benefit.BenefitBadge.STAR;
import static christmas.enums.badge.benefit.BenefitBadge.TREE;
import static christmas.enums.menu.BeverageMenu.RED_WINE;
import static christmas.enums.menu.MainMenu.CHRISTMAS_PASTA;
import static christmas.enums.menu.MainMenu.T_BONE_STEAK;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.badge.benefit.BenefitBadge;
import christmas.enums.benefit.DiscountBenefit;
import christmas.manangers.WooWaEventManager;
import christmas.manangers.BadgeManager;
import christmas.order.Orders;
import christmas.order.OrderMenu;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeManagerListTest {

    private final static WooWaEventManager WOO_WA_EVENT_MANAGER = new WooWaEventManager();
    private final static BadgeManager BADGE_MANAGER = new BadgeManager(WOO_WA_EVENT_MANAGER);
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER,7);
    private final static LocalDate reservationSpecialDate = LocalDate.of(2023, Month.DECEMBER,10);
    private final static Integer UNDER_5000 = 4000;

    @DisplayName("총 혜택이 20_000원 이상이면 산타 뱃지를 부여한다.")
    @Test
    void returnSantaBadge() {
        //when
        BenefitBadge badgeConditionSatisfied = BADGE_MANAGER.isBadgeConditionSatisfied(reservationDate,
                SANTA.getAmount());

        //then
        assertThat(badgeConditionSatisfied).isEqualTo(SANTA);
    }

    @DisplayName("총 혜택이 10_000원 이상이면 트리 뱃지를 부여한다.")
    @Test
    void returnTreeBadge() {

        //when
        BenefitBadge badgeConditionSatisfied = BADGE_MANAGER.isBadgeConditionSatisfied(reservationSpecialDate,
                TREE.getAmount());

        //then
        assertThat(badgeConditionSatisfied).isEqualTo(TREE);
    }

    @DisplayName("총 혜택이 5000원 이상이면 별 뱃지를 부여한다.")
    @Test
    void returnStarBadge() {
        //when
        BenefitBadge badgeConditionSatisfied = BADGE_MANAGER.isBadgeConditionSatisfied(reservationDate,
                STAR.getAmount());

        //then
        assertThat(badgeConditionSatisfied).isEqualTo(STAR);

    }

    @DisplayName("총 혜택이 5000원 미만이면 NONE 뱃지를 부여한다.")
    @Test
    void returnNoneBadge() {
        //when
        BenefitBadge badgeConditionSatisfied = BADGE_MANAGER.isBadgeConditionSatisfied(reservationDate,
                UNDER_5000);

        //then
        assertThat(badgeConditionSatisfied).isEqualTo(NONE);

    }
}