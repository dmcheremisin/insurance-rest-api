package com.scale.global.insurance.app.engine;

import java.math.BigDecimal;

public interface TariffRate {
    BigDecimal getRate(Integer age);

    BigDecimal getProgramPrice();
}
