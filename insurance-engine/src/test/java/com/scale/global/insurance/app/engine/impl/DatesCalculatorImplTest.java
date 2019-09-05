package com.scale.global.insurance.app.engine.impl;

import com.scale.global.insurance.app.engine.DatesCalculator;
import com.scale.global.insurance.app.engine.impl.DatesCalculatorImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DatesCalculatorImplTest {

    private DatesCalculator datesCalculator = new DatesCalculatorImpl();
    private static final LocalDate DATE = LocalDate.of(2019, 9, 2);

    @Test
    void yearsSinceInceptionThomasDanzig() {
        LocalDate inceptionDate = LocalDate.of(2010, 1, 1);
        LocalDate baseDate = LocalDate.of(2019, 9, 2);
        int inception = datesCalculator.yearsSinceInception(inceptionDate, baseDate);
        assertEquals(9, inception);
    }

    @Test
    void yearsSinceInceptionEndOfMonth() {
        LocalDate inceptionDate = LocalDate.of(2018, 12, 31);
        LocalDate baseDate = LocalDate.of(2019, 12, 1);
        int inception = datesCalculator.yearsSinceInception(inceptionDate, baseDate);
        assertEquals(1, inception);
    }

    @Test
    void yearsSinceInceptionNotEnoughMonth() {
        LocalDate inceptionDate = LocalDate.of(2018, 12, 31);
        LocalDate baseDate = LocalDate.of(2019, 11, 30);
        int inception = datesCalculator.yearsSinceInception(inceptionDate, baseDate);
        assertEquals(0, inception);
    }

    @Test
    void ageCalculator18() {
        int age = datesCalculator.getAgeToDate(LocalDate.of(2001, 1, 11), DATE);
        assertEquals(18, age);
    }

    @Test
    void ageCalculator29() {
        int age = datesCalculator.getAgeToDate(LocalDate.of(1989, 9, 3), DATE);
        assertEquals(29, age);
    }

    @Test
    void ageCalculator30() {
        int age = datesCalculator.getAgeToDate(LocalDate.of(1989, 9, 1), DATE);
        assertEquals(30, age);
        age = datesCalculator.getAgeToDate(LocalDate.of(1989, 9, 2), DATE);
        assertEquals(30, age);
    }
}