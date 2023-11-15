package christmas.dto;

import java.util.Map;

public class BenefitDto {

    private final Map<String, Integer> benefit;

    public BenefitDto(final Map<String, Integer> benefit) {
        this.benefit = benefit;
    }

    public Map<String, Integer> getBenefit() {
        return benefit;
    }
}
