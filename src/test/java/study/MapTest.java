package study;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.discount.ChristmasDiscount;
import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.SpecialDiscount;
import christmas.promotion.domain.event.gift.ChampagneGift;
import christmas.promotion.domain.event.gift.GiftEvent;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    private final Map<Event, Double> map = new HashMap<>();

    @Test
    void test() {

        map.put(ChristmasDiscount.INSTANCE, 0.0);
        map.put(SpecialDiscount.INSTANCE, 0.0);
        map.put(ChampagneGift.INSTANCE, 0.0);

        System.out.println("name        " + map.getClass().getName());
        System.out.println("class       " + map.getClass().getClass());
        System.out.println("simpleName      " + map.getClass().getSimpleName());
        System.out.println("classes     " + map.getClass().getClasses());
        System.out.println("classLoader     " + map.getClass().getClassLoader());
        System.out.println(map.getClass().getAnnotatedInterfaces());
        System.out.println(map.getClass().getAnnotatedSuperclass());
        System.out.println("interfaces   " + map.getClass().getInterfaces());

        for (Event commonEvent : map.keySet()) {
            Class<?>[] interfaces = commonEvent.getClass().getInterfaces();
            for (Class<?> anInterface : interfaces) {
                System.out.println(anInterface.getSimpleName());
            }
        }

        for (Event commonEvent : map.keySet()) {
            if (commonEvent instanceof DiscountEvent) {
                System.out.println(commonEvent + "DiscountEvnet");
            }
            if (commonEvent instanceof GiftEvent) {
                System.out.println(commonEvent + "GiftEvent");
            }
        }
    }
}
