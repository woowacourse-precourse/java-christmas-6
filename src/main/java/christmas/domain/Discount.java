package christmas.domain;

public class Discount {
    private int christmasDiscount = 0;
    private int normalDiscount = 0;
    private int specialDiscount = 0;

    public void setChristmasDiscount(int christmasDiscount) {
        this.christmasDiscount = christmasDiscount;
    }

    public void setNormalDiscount(int normalDiscount) {
        this.normalDiscount = normalDiscount;
    }

    public void setSpecialDiscount(int specialDiscount) {
        this.specialDiscount = specialDiscount;
    }
}
