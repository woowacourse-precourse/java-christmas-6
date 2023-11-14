package christmas.domain;

public class Discount {
    private int christmasDiscount = 0;
    private int normalDiscount = 0;
    private int specialDiscount = 0;
    private int givingEventDiscount = 0;

    public void setChristmasDiscount(int christmasDiscount) {
        this.christmasDiscount = christmasDiscount;
    }

    public void setNormalDiscount(int normalDiscount) {
        this.normalDiscount = normalDiscount;
    }

    public void setSpecialDiscount(int specialDiscount) {
        this.specialDiscount = specialDiscount;
    }

    public void setGivingEventDiscount(int givingEventDiscount) {
        this.givingEventDiscount = givingEventDiscount;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getNormalDiscount() {
        return normalDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getGivingEventDiscount() {
        return givingEventDiscount;
    }
}
