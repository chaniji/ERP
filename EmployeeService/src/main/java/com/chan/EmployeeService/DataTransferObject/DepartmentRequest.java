package com.chan.EmployeeService.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

  @NotBlank(message = "Department Name is Required")
  private String name;
  private String description;

}
