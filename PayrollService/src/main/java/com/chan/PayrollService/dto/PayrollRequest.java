package com.chan.PayrollService.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PayrollRequest {
    private Long employeeId;
    private BigDecimal basicSalary;
    private String month;
}
