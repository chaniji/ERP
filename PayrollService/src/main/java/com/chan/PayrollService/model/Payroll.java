package com.chan.PayrollService.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payrolls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private BigDecimal basicSalary;

    @Column(nullable = false)
    private BigDecimal netSalary;

    @Column(nullable = false)
    private String month;

    @Enumerated(EnumType.STRING)
    private PayrollStatus status;

    private LocalDateTime processedAt;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = PayrollStatus.PENDING;
        }
    }
}
