package com.scale.global.insurance.app.engine.impl;

import com.scale.global.insurance.app.engine.DatesCalculator;
import com.scale.global.insurance.app.engine.PriceCalculator;
import com.scale.global.insurance.app.engine.TariffRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PriceCalculatorImplTest {

    @Mock
    TariffRate tariffRate;

    @Mock
    DatesCalculator datesCalculator;

    PriceCalculator priceCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        priceCalculator = new PriceCalculatorImpl(tariffRate, datesCalculator);
        when(tariffRate.getProgramPrice()).thenReturn(new BigDecimal("300.00"));
    }

    /**
     * (1 * 300.00) * (1 - 0.09) = 300.00 * 0.91 = EUR 273,00
     */
    @Test
    void calculateRateThomasDanzig() {
        when(datesCalculator.getAgeToDate(any(), any())).thenReturn(29);
        when(datesCalculator.yearsSinceInception(any(), any())).thenReturn(9);
        when(tariffRate.getRate(29)).thenReturn(new BigDecimal("1.0"));
        BigDecimal price = priceCalculator.calculateRate(LocalDate.now(), LocalDate.now());
        assertEquals(new BigDecimal("273.00"), price);
    }

    /**
     * (0 * 300.00) * (1 - 0.39) = 0.00 * 0.61 = EUR 0,00
     */
    @Test
    void calculateRatePetraHeisenberg() {
        when(datesCalculator.getAgeToDate(any(), any())).thenReturn(69);
        when(datesCalculator.yearsSinceInception(any(), any())).thenReturn(39);
        when(tariffRate.getRate(69)).thenReturn(new BigDecimal("0.0"));
        BigDecimal price = priceCalculator.calculateRate(LocalDate.now(), LocalDate.now());
        assertEquals(new BigDecimal("0.00"), price);
    }

    /**
     * (1.25 * 300.00) * (1 - 0.19) = 300.00 * 0.81 = EUR 303.75
     */
    @Test
    void calculateRateVincentVega() {
        when(datesCalculator.getAgeToDate(any(), any())).thenReturn(59);
        when(tariffRate.getRate(59)).thenReturn(new BigDecimal("1.25"));
        when(datesCalculator.yearsSinceInception(any(), any())).thenReturn(19);
        BigDecimal price = priceCalculator.calculateRate(LocalDate.now(), LocalDate.now());
        assertEquals(new BigDecimal("303.75"), price);
    }

    /**
     * (1 * 300.00) * (1 - 0.11) = 300.00 * 0.89 = EUR 267,00
     */
    @Test
    void calculateRateJeffSciarra() {
        when(datesCalculator.getAgeToDate(any(), any())).thenReturn(35);
        when(tariffRate.getRate(35)).thenReturn(new BigDecimal("1.00"));
        when(datesCalculator.yearsSinceInception(any(), any())).thenReturn(11);
        BigDecimal price = priceCalculator.calculateRate(LocalDate.now(), LocalDate.now());
        assertEquals(new BigDecimal("267.00"), price);
    }



    /**
     * (1.25 * 300.00) * (1 - 0.33) = 375.00 * 0.67 = EUR 251,25
     */
    @Test
    void calculateRateIanMalholz() {
        when(datesCalculator.getAgeToDate(any(), any())).thenReturn(66);
        when(tariffRate.getRate(66)).thenReturn(new BigDecimal("1.25"));
        when(datesCalculator.yearsSinceInception(any(), any())).thenReturn(33);
        BigDecimal price = priceCalculator.calculateRate(LocalDate.now(), LocalDate.now());
        assertEquals(new BigDecimal("251.25"), price);
    }



}