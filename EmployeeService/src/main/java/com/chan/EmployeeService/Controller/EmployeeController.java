package com.chan.EmployeeService.Controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chan.EmployeeService.Service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import com.chan.EmployeeService.DataTransferObject.MessageResponse;
import jakarta.validation.Valid;
import com.chan.EmployeeService.DataTransferObject.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import com.chan.EmployeeService.DataTransferObject.EmployeeRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employeee Api", description = "CRUD operation for employees")
public class EmployeeController {
  private final EmployeeService EService;

  @PostMapping
  @Operation(summary = "Create a New Employee")
  public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest ERequest) {
    EmployeeResponse RS = EService.createEmployee(ERequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(RS);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a Employee By Id")
  public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable long id) {
    return ResponseEntity.status(HttpStatus.OK).body(EService.getEmployeeById(id));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a Employee By Id")
  public ResponseEntity<MessageResponse> deleteEmployeeById(@PathVariable long id) {
    return ResponseEntity.status(HttpStatus.OK).body(EService.deleteEmployeeById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a Employee By Id")
  public ResponseEntity<EmployeeResponse> updateEmployeeById(@RequestBody EmployeeRequest ERequest,
      @PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(EService.updateEmployeeById(ERequest, id));
  }

  @GetMapping
  @Operation(summary = "Get  All Employee")
  public ResponseEntity<Page<EmployeeResponse>> getAllEmployee(@PageableDefault(size = 10) Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(EService.getAllEmployee(pageable));
  }

  @GetMapping("/departments/{id}")
  @Operation(summary = "Get a Emplooyees By Department Id")
  public ResponseEntity<List<EmployeeResponse>> getEmployeeByDepartmentID(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(EService.getEmployeebyDepartmentId(id));
  }

}
