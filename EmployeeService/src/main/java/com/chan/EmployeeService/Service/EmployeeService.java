package com.chan.EmployeeService.Service;

import com.chan.EmployeeService.DataTransferObject.EmployeeRequest;
import com.chan.EmployeeService.DataTransferObject.EmployeeResponse;
import com.chan.EmployeeService.DataTransferObject.MessageResponse;
import com.chan.EmployeeService.Entity.Department;
import com.chan.EmployeeService.Entity.Employee;
import com.chan.EmployeeService.Exceptions.ResourcenotfoundException;
import com.chan.EmployeeService.Repository.DepartmentRepo;
import com.chan.EmployeeService.Repository.EmployeeRepo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final DepartmentRepo DRepo;
    private final EmployeeRepo ERepo;

    public EmployeeResponse createEmployee(EmployeeRequest ERequest) {
        Employee E1 = new Employee();
        E1.setFirstName(ERequest.getFirstName());
        E1.setLastName(ERequest.getLastName());
        E1.setEmail(ERequest.getEmail());
        E1.setSalary(ERequest.getSalary());
        E1.setJoinDate(ERequest.getJoinDate());
        Department saved = DRepo.findById(
            ERequest.getDepartmentId()
        ).orElseThrow(() ->
            new ResourcenotfoundException(
                "Id Could not Found:" + ERequest.getDepartmentId()
            )
        );
        E1.setDepartment(saved);
        return maptoResponse(ERepo.save(E1));
    }

    @Cacheable(value = "employee", key = "#id")
    public EmployeeResponse getEmployeeById(Long id) {
        Employee saved = ERepo.findById(id).orElseThrow(() ->
            new ResourcenotfoundException("Id Could not found" + id)
        );
        return maptoResponse(saved);
    }

    public MessageResponse deleteEmployeeById(Long id) {
        Employee saved = ERepo.findById(id).orElseThrow(() ->
            new ResourcenotfoundException("Id Could not found" + id)
        );
        ERepo.delete(saved);
        return new MessageResponse("Deleted Successfully");
    }

    public EmployeeResponse updateEmployeeById(
        EmployeeRequest ERequest,
        Long id
    ) {
        Employee saved = ERepo.findById(id).orElseThrow(() ->
            new ResourcenotfoundException("Id Could not found" + id)
        );
        saved.setFirstName(ERequest.getFirstName());
        saved.setLastName(ERequest.getLastName());
        saved.setEmail(ERequest.getEmail());
        saved.setSalary(ERequest.getSalary());
        saved.setJoinDate(ERequest.getJoinDate());
        Department Dsaved = DRepo.findById(
            ERequest.getDepartmentId()
        ).orElseThrow(() ->
            new ResourcenotfoundException(
                "Id Could not Found:" + ERequest.getDepartmentId()
            )
        );
        saved.setDepartment(Dsaved);

        return maptoResponse(ERepo.save(saved));
    }

    @Cacheable(value = "employees", key = "0")
    public Page<EmployeeResponse> getAllEmployee(Pageable pageable) {
        return ERepo.findAll(pageable).map(this::maptoResponse);
    }

    @Cacheable(value = "employee", key = "#id")
    public List<EmployeeResponse> getEmployeebyDepartmentId(Long id) {
        Department saved = DRepo.findById(id).orElseThrow(() ->
            new ResourcenotfoundException("Id Could not found" + id)
        );
        return ERepo.findByDepartment(saved)
            .stream()
            .map(this::maptoResponse)
            .collect(Collectors.toList());
    }

    private EmployeeResponse maptoResponse(Employee Emp) {
        return new EmployeeResponse(
            Emp.getId(),
            Emp.getFirstName(),
            Emp.getLastName(),
            Emp.getSalary(),
            Emp.getJoinDate(),
            Emp.getDepartment().getId()
        );
    }
}
