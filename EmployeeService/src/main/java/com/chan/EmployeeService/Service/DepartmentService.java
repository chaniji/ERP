package com.chan.EmployeeService.Service;

import org.springframework.stereotype.Service;
import com.chan.EmployeeService.DataTransferObject.DepartmentRequest;
import com.chan.EmployeeService.DataTransferObject.DepartmentResponse;
import com.chan.EmployeeService.Entity.Department;
import com.chan.EmployeeService.Repository.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import com.chan.EmployeeService.Exceptions.ResourcenotfoundException;
import com.chan.EmployeeService.DataTransferObject.MessageResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

  private final DepartmentRepo DRepo;

  public DepartmentResponse createDepartment(DepartmentRequest DRequest) {
    Department d1 = new Department();
    d1.setName(DRequest.getName());
    d1.setDescription(DRequest.getDescription());
    Department saved = DRepo.save(d1);
    return maptoResponse(saved);
  }

  public DepartmentResponse getDepartmentById(Long id) {
    Department d1 = DRepo.findById(id)
        .orElseThrow(() -> new ResourcenotfoundException("Department Id Not Found" + id));
    return maptoResponse(d1);
  }

  public MessageResponse deleteDepartmentById(Long id) {
    Department d1 = DRepo.findById(id)
        .orElseThrow(() -> new ResourcenotfoundException("Department Id Not Found" + id));
    DRepo.delete(d1);
    return new MessageResponse("Deleted Successfully");
  }

  public DepartmentResponse updateDepartmentById(DepartmentRequest DRequest, Long id) {
    Department d1 = DRepo.findById(id)
        .orElseThrow(() -> new ResourcenotfoundException("Department Id Not Found" + id));
    d1.setName(DRequest.getName());
    d1.setDescription(DRequest.getDescription());
    Department saved = DRepo.save(d1);
    return maptoResponse(saved);
  }

  public List<DepartmentResponse> getAllDepartment() {
    return DRepo.findAll().stream().map(this::maptoResponse).collect(Collectors.toList());
  }

  private DepartmentResponse maptoResponse(Department d) {
    return new DepartmentResponse(d.getId(), d.getName(), d.getDescription());
  }

}
