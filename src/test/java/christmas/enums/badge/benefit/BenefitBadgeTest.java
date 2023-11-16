package christmas.enums.badge.benefit;

import static christmas.enums.badge.benefit.BenefitBadge.NONE;
import static christmas.enums.badge.benefit.BenefitBadge.SANTA;
import static christmas.enums.badge.benefit.BenefitBadge.STAR;
import static christmas.enums.badge.benefit.BenefitBadge.TREE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitBadgeTest {

    private final static Integer UNDER_5000 = 4000;

    @DisplayName("총 혜택이 20_000원 이상이면 산타 뱃지를 부여한다.")
    @Test
    void returnSantaBadge() {
        //when
        BenefitBadge badgeConditionSatisfied = BenefitBadge.determineBadge(SANTA.getAmount());

        //then
        assertThat(badgeConditionSatisfied).isEqualTo(SANTA);
    }

    @DisplayName("총 혜택이 10_000원 이상이면 트리 뱃지를 부여한다.")
    @Test
    void returnTreeBadge() {

        //when
        BenefitBadge badgeConditionSatisfied = BenefitBadge.determineBadge(TREE.getAmount());

        //then
        assertThat(badgeConditionSatisfied).isEqualTo(TREE);
    }

    @DisplayName("총 혜택이 5000원 이상이면 별 뱃지를 부여한다.")
    @Test
    void returnStarBadge() {
        //when
        BenefitBadge badgeConditionSatisfied = BenefitBadge.determineBadge(STAR.getAmount());

        //then
        assertThat(badgeConditionSatisfied).isEqualTo(STAR);

    }

    @DisplayName("총 혜택이 5000원 미만이면 NONE 뱃지를 부여한다.")
    @Test
    void returnNoneBadge() {
        //when
        BenefitBadge badgeConditionSatisfied = BenefitBadge.determineBadge(UNDER_5000);

        //then
        assertThat(badgeConditionSatisfied).isEqualTo(NONE);

    }
}