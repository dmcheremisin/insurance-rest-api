package com.scale.global.insurance.app.engine.impl;

import com.scale.global.insurance.app.engine.TariffRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TariffRateImpl.class)
class TariffRateImplTest {

    public static final BigDecimal RATE1 = new BigDecimal("0.5");
    public static final BigDecimal RATE2 = new BigDecimal("0.75");
    public static final BigDecimal RATE3 = new BigDecimal("1.00");
    public static final BigDecimal RATE4 = new BigDecimal("1.25");
    public static final BigDecimal RATE5 = new BigDecimal("0.0");

    @Autowired
    TariffRate tariffRate;

    @Test
    void getRateUnder11() {
        BigDecimal rate = tariffRate.getRate(10);
        assertEquals(RATE1, rate);
        rate = tariffRate.getRate(11);
        assertEquals(RATE1, rate);
        rate = tariffRate.getRate(12);
        assertEquals(RATE2, rate);
    }

    @Test
    void getRateUnder20() {
        BigDecimal rate = tariffRate.getRate(19);
        assertEquals(RATE2, rate);
        rate = tariffRate.getRate(20);
        assertEquals(RATE2, rate);
        rate = tariffRate.getRate(21);
        assertEquals(RATE3, rate);
    }

    @Test
    void getRateUnder44() {
        BigDecimal rate = tariffRate.getRate(43);
        assertEquals(RATE3, rate);
        rate = tariffRate.getRate(44);
        assertEquals(RATE3, rate);
        rate = tariffRate.getRate(45);
        assertEquals(RATE4, rate);
    }

    @Test
    void getRateUnder66() {
        BigDecimal rate = tariffRate.getRate(65);
        assertEquals(RATE4, rate);
        rate = tariffRate.getRate(66);
        assertEquals(RATE4, rate);
        rate = tariffRate.getRate(67);
        assertEquals(RATE5, rate);
    }

    @Test
    void getRateUnder67AndMore() {
        BigDecimal rate = tariffRate.getRate(67);
        assertEquals(RATE5, rate);
        rate = tariffRate.getRate(69);
        assertEquals(RATE5, rate);
    }

    @Test
    void getProgramPrice() {
        BigDecimal programPrice = tariffRate.getProgramPrice();
        assertEquals(new BigDecimal("300.00"), programPrice);
    }
}