package christmas.domain.benefit.gifts;

import christmas.domain.Orders;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.BenefitType;
import christmas.domain.menu.MenuAndCount;
import java.util.List;

public class Gift implements Benefit {
    private final BenefitType benefitType;
    private final String benefitName;
    private final Orders orders;

    public Gift(String benefitName, Orders orders) {
        this.benefitType = BenefitType.GIFTS;
        this.benefitName = benefitName;
        this.orders = orders;
    }

    @Override
    public String getBenefitName() {
        return benefitName;
    }

    @Override
    public int getBenefitPrice() {
        return orders.getTotalPrice() * -1;
    }

    @Override
    public boolean isTypeOf(BenefitType benefitType) {
        return this.benefitType == benefitType;
    }

    public List<MenuAndCount> getOrders() {
        return orders.getOrders();
    }
}
