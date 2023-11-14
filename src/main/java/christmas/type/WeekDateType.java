package christmas.type;

public enum WeekDateType {
    ONE(1), TWO(2), EIGHT(8), NINE(9),
    FIFTEEN(15), SIXTEEN(16), TWENTY_TWO(22),
    TWENTY_THREE(23), TWENTY_NINE(29), THIRTY(30);

    private int date;

    WeekDateType(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public static boolean isDateIncluded(int date) {
        for (WeekDateType weekDate : WeekDateType.values()) {
            if (weekDate.getDate() == date) {
                return true;
            }
        }
        return false;
    }
}
