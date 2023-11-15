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

    public EventBadge giveEventBadge (int totalAmount) {
        if (totalAmount >= SANTA.minTotalAmount) {
            return SANTA;
        }
        if (totalAmount >= TREE.minTotalAmount) {
            return TREE;
        }
        if (totalAmount >= STAR.minTotalAmount) {
            return STAR;
        }
        return null;
    }

}
// TODO: 이벤트 배지의 경우 다른 프로모션들과 다른 금액 조건을 가지고 있음
