package com.chan.PayrollService.repository;

import com.chan.PayrollService.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    List<Payroll> findByEmployeeId(Long employeeId);
}
