package com.chan.EmployeeService.Repository;

import com.chan.EmployeeService.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
