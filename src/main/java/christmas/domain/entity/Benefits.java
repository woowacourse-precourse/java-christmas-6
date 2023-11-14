package christmas.domain.entity;

public class Benefits {

    private static int totalAmountBeforeDiscount;

    public static int getTotalAmountBeforeDiscount() {
        return totalAmountBeforeDiscount;
    }

    public static void setTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        Benefits.totalAmountBeforeDiscount = totalAmountBeforeDiscount;
    }

    public static String getGiftMenu() {
        return giftMenu;
    }

    public static void setGiftMenu(String giftMenu) {
        Benefits.giftMenu = giftMenu;
    }

    public static int getdDayDiscountAmount() {
        return dDayDiscountAmount;
    }

    public static void setdDayDiscountAmount(int dDayDiscountAmount) {
        Benefits.dDayDiscountAmount = dDayDiscountAmount;
    }

    public static int getWeekdayDiscountAmount() {
        return weekdayDiscountAmount;
    }

    public static void setWeekdayDiscountAmount(int weekdayDiscountAmount) {
        Benefits.weekdayDiscountAmount = weekdayDiscountAmount;
    }

    public static int getWeekendDiscountAmount() {
        return weekendDiscountAmount;
    }

    public static void setWeekendDiscountAmount(int weekendDiscountAmount) {
        Benefits.weekendDiscountAmount = weekendDiscountAmount;
    }

    public static int getSpecialDayDiscountAmount() {
        return specialDayDiscountAmount;
    }

    public static void setSpecialDayDiscountAmount(int specialDayDiscountAmount) {
        Benefits.specialDayDiscountAmount = specialDayDiscountAmount;
    }

    public static int getGiftEventBenefitAmount() {
        return giftEventBenefitAmount;
    }

    public static void setGiftEventBenefitAmount(int giftEventBenefitAmount) {
        Benefits.giftEventBenefitAmount = giftEventBenefitAmount;
    }

    public static int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public static void setTotalBenefitAmount(int totalDiscountAmount) {
        Benefits.totalBenefitAmount = totalDiscountAmount;
    }

    public static int getTotalAmountAfterDiscount() {
        return totalAmountAfterDiscount;
    }

    public static void setTotalAmountAfterDiscount(int totalAmountAfterDiscount) {
        Benefits.totalAmountAfterDiscount = totalAmountAfterDiscount;
    }

    public static String getDecemberEventBadge() {
        return DecemberEventBadge;
    }

    public static void setDecemberEventBadge(String decemberEventBadge) {
        DecemberEventBadge = decemberEventBadge;
    }

    private static String giftMenu;

    private static int dDayDiscountAmount;
    private static int weekdayDiscountAmount;
    private static int weekendDiscountAmount;
    private static int specialDayDiscountAmount;
    private static int giftEventBenefitAmount;

    private static int totalBenefitAmount;

    private static int totalAmountAfterDiscount;

    private static String DecemberEventBadge;

    public static int accountTotalBenefit(){
        totalBenefitAmount =
                dDayDiscountAmount
                + weekdayDiscountAmount
                + weekendDiscountAmount
                + specialDayDiscountAmount
                + giftEventBenefitAmount;
        return totalBenefitAmount;
    }

    public static int accountTotalAmountAfterDiscount(){
        totalAmountAfterDiscount =
                totalAmountBeforeDiscount
                - totalBenefitAmount;
        return totalAmountAfterDiscount;
    }

}
