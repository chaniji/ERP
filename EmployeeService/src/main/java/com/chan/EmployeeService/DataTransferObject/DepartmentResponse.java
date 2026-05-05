package com.chan.EmployeeService.DataTransferObject;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse implements Serializable {

    private Long id;
    private String name;
    private String description;
}
