package com.chan.PayrollService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEvent {
    private Long employeeId;
    private String type; // e.g., CREATED, UPDATED, DELETED
    private String name;
    private String email;
    private String department;
}
