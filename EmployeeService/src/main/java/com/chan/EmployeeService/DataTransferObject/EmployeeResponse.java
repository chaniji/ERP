package com.chan.EmployeeService.DataTransferObject;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Double salary;
    private LocalDate joinDate;
    private Long departmentId;
}
