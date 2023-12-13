package christmas.domain.money;

import christmas.exception.PromotionExceptionMaker;

public class Money implements Monetary {
    private final int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < 0) {
            throw PromotionExceptionMaker.MONEY_MUST_NOT_NEGATIVE.makeException();
        }
    }

    public Cash toCash(){
        return new Cash(this.money);
    }

    @Override
    public int getPrice() {
        return this.money;
    }
}
