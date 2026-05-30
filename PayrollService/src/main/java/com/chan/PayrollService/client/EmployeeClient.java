package com.chan.PayrollService.client;

import com.chan.PayrollService.dto.EmployeeDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping("/api/employees/{id}")
    @CircuitBreaker(name = "employeeService", fallbackMethod = "getEmployeeFallback")
    EmployeeDTO getEmployee(@PathVariable("id") Long id);

    default EmployeeDTO getEmployeeFallback(Long id, Throwable t) {
        EmployeeDTO fallback = new EmployeeDTO();
        fallback.setId(id);
        fallback.setName("Unknown (Service Unavailable)");
        return fallback;
    }
}
