package christmas.model;

public enum EventBadge {
    STAR("별", 5000),
    TREE("트리",10000),
    SANTA("산타",20000);

    private String description;
    private int minTotalAmount;

    EventBadge (String description,int minTotalAmount) {
        this.description = description;
        this.minTotalAmount = minTotalAmount;
    }

    public static EventBadge giveEventBadge (int totalBenefit) {
        EventBadge result = null;
        if (totalBenefit >= SANTA.minTotalAmount) {
            result = SANTA;
        }
        else if (totalBenefit >= STAR.minTotalAmount) {
            result = STAR;
        }
        else if (totalBenefit >= TREE.minTotalAmount) {
            result = TREE;
        }
        return result;
    }

    @Override
    public String toString() {
        return description;
    }
}
