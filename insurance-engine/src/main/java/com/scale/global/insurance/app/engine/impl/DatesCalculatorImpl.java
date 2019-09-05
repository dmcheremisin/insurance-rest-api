package com.scale.global.insurance.app.engine.impl;

import com.scale.global.insurance.app.engine.DatesCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class DatesCalculatorImpl implements DatesCalculator {

    @Override
    public int yearsSinceInception(LocalDate inceptionDate, LocalDate baseDate) {
        Period diff = Period.between(inceptionDate.withDayOfMonth(1), baseDate);
        int years = diff.getYears();
        return years;
    }

    @Override
    public int getAgeToDate(LocalDate date, LocalDate baseDate) {
        Period periodBetween = Period.between(date, baseDate);
        return periodBetween.getYears();
    }

}
