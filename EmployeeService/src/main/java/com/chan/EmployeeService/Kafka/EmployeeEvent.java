package com.chan.EmployeeService.Kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEvent {
    private String id;
    private String type; // CREATED, UPDATED, DELETED
    private String message;
    private String email;
}
