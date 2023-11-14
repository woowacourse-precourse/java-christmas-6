package christmas.type;

public enum SpecialDateType {
    THREE(3), TEN(10), SEVENTEEN(17), TWENTY_FOUR(24),
    TWENTY_FIVE(25), THIRTY_ONE(31);

    private int date;

    SpecialDateType(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public static boolean isDateIncluded(int date) {
        for (SpecialDateType specialDateType : SpecialDateType.values()) {
            if (specialDateType.getDate() == date) {
                return true;
            }
        }
        return false;
    }
}
