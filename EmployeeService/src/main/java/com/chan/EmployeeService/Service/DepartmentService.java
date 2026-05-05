package com.chan.EmployeeService.Service;

import com.chan.EmployeeService.DataTransferObject.DepartmentRequest;
import com.chan.EmployeeService.DataTransferObject.DepartmentResponse;
import com.chan.EmployeeService.DataTransferObject.MessageResponse;
import com.chan.EmployeeService.Entity.Department;
import com.chan.EmployeeService.Exceptions.ResourcenotfoundException;
import com.chan.EmployeeService.Repository.DepartmentRepo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Cacheable(value = "department", key = "#id")
    public DepartmentResponse getDepartmentById(Long id) {
        Department d1 = DRepo.findById(id).orElseThrow(() ->
            new ResourcenotfoundException("Department Id Not Found" + id)
        );
        return maptoResponse(d1);
    }

    public MessageResponse deleteDepartmentById(Long id) {
        Department d1 = DRepo.findById(id).orElseThrow(() ->
            new ResourcenotfoundException("Department Id Not Found" + id)
        );
        DRepo.delete(d1);
        return new MessageResponse("Deleted Successfully");
    }

    public DepartmentResponse updateDepartmentById(
        DepartmentRequest DRequest,
        Long id
    ) {
        Department d1 = DRepo.findById(id).orElseThrow(() ->
            new ResourcenotfoundException("Department Id Not Found" + id)
        );
        d1.setName(DRequest.getName());
        d1.setDescription(DRequest.getDescription());
        Department saved = DRepo.save(d1);
        return maptoResponse(saved);
    }

    @Cacheable(value = "departments", key = "0")
    public List<DepartmentResponse> getAllDepartment() {
        return DRepo.findAll()
            .stream()
            .map(this::maptoResponse)
            .collect(Collectors.toList());
    }

    private DepartmentResponse maptoResponse(Department d) {
        return new DepartmentResponse(
            d.getId(),
            d.getName(),
            d.getDescription()
        );
    }
}
