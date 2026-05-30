package com.chan.PayrollService.service;

import com.chan.PayrollService.dto.PayrollRequest;
import com.chan.PayrollService.dto.PayrollResponse;
import com.chan.PayrollService.model.Payroll;
import com.chan.PayrollService.repository.PayrollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PayrollServiceTest {

    @Mock
    private PayrollRepository payrollRepository;

    @Mock
    private SalaryCalculationService salaryCalculationService;

    @InjectMocks
    private PayrollService payrollService;

    private PayrollRequest request;
    private Payroll payroll;

    @BeforeEach
    void setUp() {
        request = new PayrollRequest();
        request.setEmployeeId(1L);
        request.setBasicSalary(new BigDecimal("1000.00"));
        request.setMonth("MAY");

        payroll = Payroll.builder()
                .id(1L)
                .employeeId(1L)
                .basicSalary(new BigDecimal("1000.00"))
                .netSalary(new BigDecimal("900.00"))
                .month("MAY")
                .status(com.chan.PayrollService.model.PayrollStatus.PENDING)
                .build();
    }

    @Test
    void createPayroll_ShouldReturnResponse() {
        when(salaryCalculationService.calculateNetSalary(any())).thenReturn(new BigDecimal("900.00"));
        when(payrollRepository.save(any())).thenReturn(payroll);

        PayrollResponse response = payrollService.createPayroll(request);

        assertNotNull(response);
        assertEquals(new BigDecimal("900.00"), response.getNetSalary());
        verify(payrollRepository, times(1)).save(any());
    }

    @Test
    void getPayrollById_ShouldReturnResponse() {
        when(payrollRepository.findById(1L)).thenReturn(Optional.of(payroll));

        PayrollResponse response = payrollService.getPayrollById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }
}
