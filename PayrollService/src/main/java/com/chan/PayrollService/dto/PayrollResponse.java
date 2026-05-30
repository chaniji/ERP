package com.chan.PayrollService.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PayrollResponse {
    private Long id;
    private Long employeeId;
    private BigDecimal basicSalary;
    private BigDecimal netSalary;
    private String month;
    private String status;
    private LocalDateTime processedAt;
}
