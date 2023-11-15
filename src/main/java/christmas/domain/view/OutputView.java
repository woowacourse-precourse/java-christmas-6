package christmas.domain.view;

import christmas.domain.constants.DiscountPolicyEnum;
import christmas.domain.entity.Benefits;
import christmas.domain.entity.Order;
import christmas.domain.util.Printer;

import java.text.DecimalFormat;

public class OutputView {
    Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

    DecimalFormat decimalFormat = new DecimalFormat("#,###");

    private String convertDecimalFormat(int number){
        return decimalFormat.format(number);
    }

    public void printPreface(){
        printer.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenu(Order order) {
        printer.println("");
        printer.println("<주문 메뉴>");
        order.getOrderMap().forEach((key, value) -> printer.println(key.getName() + " " + value + "개"));
        printer.println("");
    }

    public void printBenefit() {
        printer.println("<할인 전 총주문 금액>");
        printer.println(convertDecimalFormat(Benefits.getTotalAmountBeforeDiscount()) + "원");
        printer.println("");
        printer.println("<증정 메뉴>");
        printGift();
        printer.println("<혜택 내역>");
        printBenefitDetails();
        printer.println("<총혜택 금액>");
        printTotalBenefits();
        printer.println("<할인 후 예상 결제 금액>");
        printer.println(convertDecimalFormat(Benefits.accountTotalAmountAfterDiscount()) + "원");
        printer.println("");
        printer.println("<12월 이벤트 배지>");
        printDecemberEventBadge();
    }

    private void printGift(){
        if(Benefits.getGiftEventBenefitAmount()==0){
            printer.println("없음");
            printer.println("");
            return;
        }
        printer.println("샴페인 1개");
        printer.println("");
    }

    private void printBenefitDetails(){
        if(isToTalDiscountZero()){
            printer.println("없음");
            printer.println("");
            return;
        }
        if(Benefits.getdDayDiscountAmount()!=0){
            printer.println("크리스마스 디데이 할인: -" + convertDecimalFormat(Benefits.getdDayDiscountAmount()) + "원");
        }
        if(Benefits.getWeekdayDiscountAmount()!=0){
            printer.println("평일 할인: -" + convertDecimalFormat(Benefits.getWeekdayDiscountAmount()) + "원");
        }
        if(Benefits.getWeekendDiscountAmount()!=0){
            printer.println("주말 할인: -" + convertDecimalFormat(Benefits.getWeekendDiscountAmount()) + "원");
        }
        if(Benefits.getSpecialDayDiscountAmount()!=0){
            printer.println("특별 할인: -" + convertDecimalFormat(Benefits.getSpecialDayDiscountAmount()) + "원");
        }
        if(Benefits.getGiftEventBenefitAmount()!=0){
            printer.println("증정 이벤트: -" + convertDecimalFormat(Benefits.getGiftEventBenefitAmount()) + "원");
        }
        printer.println("");
    }

    private void printTotalBenefits(){
        if(isToTalDiscountZero()){
            printer.println("0원");
            printer.println("");
            return;
        }
        printer.println("-" + convertDecimalFormat(Benefits.getTotalBenefitAmount()) + "원");
        printer.println("");
    }

    private void printDecemberEventBadge(){
        if(Benefits.getDecemberEventBadge() == null){
            printer.println("없음");
            printer.println("");
            return;
        }
        printer.println(Benefits.getDecemberEventBadge());
        printer.println("");
    }

    private boolean isToTalDiscountZero(){
        if(Benefits.getTotalAmountBeforeDiscount()< DiscountPolicyEnum.Discount.MINIMUM_AMOUNT_TO_DISCOUNT.getValue()){
            return true;
        }
        int totalDiscount = Benefits.getdDayDiscountAmount()
                + Benefits.getWeekdayDiscountAmount()
                + Benefits.getWeekendDiscountAmount()
                + Benefits.getSpecialDayDiscountAmount()
                + Benefits.getGiftEventBenefitAmount();
        return totalDiscount == 0;
    }
}
