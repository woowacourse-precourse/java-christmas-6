package christmas.promotion.dto;

import christmas.promotion.domain.menu.Menu;
import christmas.promotion.vo.Quantity;

import java.util.Map;

public record GiftMenusDto(Map<Menu, Quantity> giftMenus) {
    public int getGiftMenusSize() {
        return giftMenus.size();
    }
}