package com.chan.EmployeeService.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.chan.EmployeeService.Service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import com.chan.EmployeeService.DataTransferObject.DepartmentRequest;
import com.chan.EmployeeService.DataTransferObject.DepartmentResponse;
import com.chan.EmployeeService.DataTransferObject.MessageResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(name = "Department Api", description = "CRUD operation for Department")
public class DepartmentController {
  private final DepartmentService DService;

  @PostMapping
  @Operation(summary = "Create a New Department")
  public ResponseEntity<DepartmentResponse> postMethodName(@RequestBody @Valid DepartmentRequest DRequest) {
    DepartmentResponse rs = DService.createDepartment(DRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(rs);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a Department By Id")
  public ResponseEntity<DepartmentResponse> getbyID(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(DService.getDepartmentById(id));

  }

  @GetMapping
  @Operation(summary = "Get All Department")
  public ResponseEntity<List<DepartmentResponse>> getallDepartment() {
    List<DepartmentResponse> DRL = DService.getAllDepartment();
    return ResponseEntity.status(HttpStatus.OK).body(DRL);

  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a Department By Id")
  public ResponseEntity<MessageResponse> deletebyID(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(DService.deleteDepartmentById(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a Department By Id")
  public ResponseEntity<DepartmentResponse> updatebyID(@RequestBody @Valid DepartmentRequest DRequest,
      @PathVariable Long id) {
    DepartmentResponse rs = DService.updateDepartmentById(DRequest, id);
    return ResponseEntity.status(HttpStatus.OK).body(rs);

  }
}
