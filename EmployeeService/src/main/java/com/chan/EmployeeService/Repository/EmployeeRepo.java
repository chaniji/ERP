package com.chan.EmployeeService.Repository;

import com.chan.EmployeeService.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import com.chan.EmployeeService.Entity.Department;
import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
  List<Employee> findByDepartment(Department department);
}
