package com.chan.EmployeeService.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import jakarta.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

  @NotBlank(message = "Name is Required ")
  private String firstName;
  private String lastName;
  @NotBlank(message = "Email is Required")
  @Email(message = "Invalid Email")
  private String email;
  @NotNull(message = "Salary is Required")
  private Double salary;
  private LocalDate joinDate;
  @NotNull(message = "Department Id is Required")
  private Long departmentId;

}
