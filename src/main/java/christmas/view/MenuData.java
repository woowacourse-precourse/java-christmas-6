package christmas.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MenuData {
    public static Map<String, Integer> menuMap = new HashMap<>();
    public static Set<String> beverageItems = new HashSet<>();

    static {
        menuMap.put("양송이수프", 6000);
        menuMap.put("타파스", 5500);
        menuMap.put("시저샐러드", 8000);
        menuMap.put("티본스테이크", 55000);
        menuMap.put("바비큐립", 54000);
        menuMap.put("해산물파스타", 35000);
        menuMap.put("크리스마스파스타", 25000);
        menuMap.put("초코케이크", 15000);
        menuMap.put("아이스크림", 5000);
        menuMap.put("제로콜라", 3000);
        menuMap.put("레드와인", 60000);
        menuMap.put("샴페인", 25000);

        beverageItems.add("제로콜라");
        beverageItems.add("레드와인");
        beverageItems.add("샴페인");
    }
}
