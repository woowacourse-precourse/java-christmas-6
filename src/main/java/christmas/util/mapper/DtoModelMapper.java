package christmas.util.mapper;

import christmas.model.order.Order;
import christmas.model.order.VisitDate;
import christmas.model.reciept.Receipt;
import christmas.util.converter.StringConverter;
import christmas.view.dto.OrderRequestDto;
import christmas.view.dto.ReceiptResponseDto;
import christmas.view.dto.VisitDayRequestDto;

public class DtoModelMapper {
    public static VisitDate dtoToVisitDate(VisitDayRequestDto dto) {
        return VisitDate.of(StringConverter.convertToInteger(dto.day()));
    }

    public static Order dtoToOrder(VisitDate visitDate, OrderRequestDto dto) {
        return Order.from(visitDate, dto.order());
    }

    public static ReceiptResponseDto ReceiptToDto(Receipt receipt) {
        VisitDate visitDate = receipt.getVisitDate();

        return new ReceiptResponseDto(visitDate.getMonth(),
                visitDate.getDay(),
                receipt.getOrders(),
                (int) receipt.getTotalOrderAmount(),
                receipt.getFreebies(),
                receipt.getBenefitRecords(),
                (int) receipt.getTotalBenefitAmount(),
                (int) receipt.getEstimatedPaymentAmount(),
                receipt.getEventBadge());
    }
}