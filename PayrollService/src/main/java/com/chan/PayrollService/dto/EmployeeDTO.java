package com.chan.PayrollService.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String department;
    private String email;
}
