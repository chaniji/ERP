package com.chan.PayrollService.service;

import com.chan.PayrollService.dto.PayrollRequest;
import com.chan.PayrollService.dto.PayrollResponse;
import com.chan.PayrollService.model.Payroll;
import com.chan.PayrollService.model.PayrollStatus;
import com.chan.PayrollService.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final SalaryCalculationService salaryCalculationService;

    public PayrollResponse createPayroll(PayrollRequest request) {
        Payroll payroll = Payroll.builder()
                .employeeId(request.getEmployeeId())
                .basicSalary(request.getBasicSalary())
                .netSalary(salaryCalculationService.calculateNetSalary(request.getBasicSalary()))
                .month(request.getMonth())
                .status(PayrollStatus.PENDING)
                .build();

        Payroll saved = payrollRepository.save(payroll);
        return mapToResponse(saved);
    }

    public PayrollResponse getPayrollById(Long id) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found: " + id));
        return mapToResponse(payroll);
    }

    public List<PayrollResponse> getPayrollsByEmployee(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<PayrollResponse> getAllPayrolls() {
        return payrollRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PayrollResponse processPayroll(Long id) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found: " + id));
        
        payroll.setStatus(PayrollStatus.PROCESSED);
        payroll.setProcessedAt(LocalDateTime.now());
        
        Payroll updated = payrollRepository.save(payroll);
        return mapToResponse(updated);
    }

    private PayrollResponse mapToResponse(Payroll payroll) {
        return PayrollResponse.builder()
                .id(payroll.getId())
                .employeeId(payroll.getEmployeeId())
                .basicSalary(payroll.getBasicSalary())
                .netSalary(payroll.getNetSalary())
                .month(payroll.getMonth())
                .status(payroll.getStatus().name())
                .processedAt(payroll.getProcessedAt())
                .build();
    }
}
