package com.chan.PayrollService.kafka;

import com.chan.PayrollService.dto.EmployeeEvent;
import com.chan.PayrollService.dto.PayrollRequest;
import com.chan.PayrollService.service.PayrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeEventConsumer {

    private final PayrollService payrollService;

    @KafkaListener(topics = "employee-events", groupId = "payroll-group")
    public void consumeEmployeeEvent(EmployeeEvent event) {
        log.info("Consumed employee event: {}", event);

        if ("CREATED".equals(event.getType())) {
            log.info("Auto-creating payroll for new employee: {}", event.getEmployeeId());
            
            PayrollRequest request = new PayrollRequest();
            request.setEmployeeId(event.getEmployeeId());
            // Default salary for new employees, can be updated later
            request.setBasicSalary(new BigDecimal("50000.00")); 
            request.setMonth(LocalDate.now().getMonth().name());
            
            payrollService.createPayroll(request);
        }
    }
}
