package com.chan.PayrollService.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SalaryCalculationService {

    public BigDecimal calculateNetSalary(BigDecimal basicSalary) {
        if (basicSalary == null) return BigDecimal.ZERO;
        // Simple 10% tax deduction
        BigDecimal tax = basicSalary.multiply(new BigDecimal("0.10"));
        return basicSalary.subtract(tax).setScale(2, RoundingMode.HALF_UP);
    }
}
