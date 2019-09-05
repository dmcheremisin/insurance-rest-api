package com.scale.global.insurance.app.engine;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PriceCalculator {
    BigDecimal calculateRate(LocalDate birthDate, LocalDate inceptionDate);
}
