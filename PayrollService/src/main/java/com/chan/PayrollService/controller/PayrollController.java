package com.chan.PayrollService.controller;

import com.chan.PayrollService.dto.PayrollRequest;
import com.chan.PayrollService.dto.PayrollResponse;
import com.chan.PayrollService.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService payrollService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PayrollResponse createPayroll(@RequestBody PayrollRequest request) {
        return payrollService.createPayroll(request);
    }

    @GetMapping
    public List<PayrollResponse> getAllPayrolls() {
        return payrollService.getAllPayrolls();
    }

    @GetMapping("/{id}")
    public PayrollResponse getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id);
    }

    @GetMapping("/employee/{empId}")
    public List<PayrollResponse> getPayrollsByEmployee(@PathVariable Long empId) {
        return payrollService.getPayrollsByEmployee(empId);
    }

    @PostMapping("/{id}/process")
    public PayrollResponse processPayroll(@PathVariable Long id) {
        return payrollService.processPayroll(id);
    }
}
