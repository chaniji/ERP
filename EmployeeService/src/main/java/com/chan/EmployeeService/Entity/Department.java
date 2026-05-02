package com.chan.EmployeeService.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.chan.EmployeeService.Entity.Employee;

@Entity

@Table(name = "departments") // Table Name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false) // Unique , Not Null
  private String name;
  private String description;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department") // Dont Load the Employee Unitl
                                                                                         // I ask , What ever do in
                                                                                         // department if affects
                                                                                         // Employees
  private List<Employee> employee;
}
