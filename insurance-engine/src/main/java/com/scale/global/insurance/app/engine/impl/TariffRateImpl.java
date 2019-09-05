package com.scale.global.insurance.app.engine.impl;

import com.scale.global.insurance.app.engine.TariffRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@PropertySource("classpath:tariffs.properties")
public class TariffRateImpl implements TariffRate {

    @Value("#{${tariffs}}")
    private Map<Integer, BigDecimal> tariffMap;

    @Value("${program.price}")
    private BigDecimal programPrice;

    @Override
    public BigDecimal getRate(Integer age) {
        BigDecimal rate = new BigDecimal("0.0");
        for (Integer tariffAge : tariffMap.keySet()) {
            if(age <= tariffAge) {
                rate = tariffMap.get(tariffAge);
                break;
            }
        }
        return rate;
    }

    @Override
    public BigDecimal getProgramPrice() {
        return programPrice;
    }

}
