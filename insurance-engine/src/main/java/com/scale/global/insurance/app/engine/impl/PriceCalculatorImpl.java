package com.scale.global.insurance.app.engine.impl;

import com.scale.global.insurance.app.engine.DatesCalculator;
import com.scale.global.insurance.app.engine.PriceCalculator;
import com.scale.global.insurance.app.engine.TariffRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PriceCalculatorImpl implements PriceCalculator {

    private BigDecimal HUNDRED = new BigDecimal("100");

    private TariffRate tariffRate;
    private DatesCalculator datesCalculator;


    public PriceCalculatorImpl(TariffRate tariffRate, DatesCalculator datesCalculator) {
        this.tariffRate = tariffRate;
        this.datesCalculator = datesCalculator;
    }

    @Override
    public BigDecimal calculateRate(LocalDate birthDate, LocalDate inceptionDate) {
        int customerAge = datesCalculator.getAgeToDate(birthDate, LocalDate.now());
        int yearsSinceInception = datesCalculator.yearsSinceInception(inceptionDate, LocalDate.now());
        BigDecimal rate = tariffRate.getRate(customerAge);
        BigDecimal programPrice = tariffRate.getProgramPrice();
        BigDecimal yearsDiscount = new BigDecimal(yearsSinceInception);
        BigDecimal calculatedRate = programPrice.multiply(rate)
                .multiply(HUNDRED.subtract(yearsDiscount).divide(HUNDRED, 2, BigDecimal.ROUND_UNNECESSARY));
        return calculatedRate.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}
