package com.scale.global.insurance.app.engine;

import java.time.LocalDate;

public interface DatesCalculator {
    int yearsSinceInception(LocalDate inceptionDate, LocalDate baseDate);

    int getAgeToDate(LocalDate date, LocalDate baseDate);
}
