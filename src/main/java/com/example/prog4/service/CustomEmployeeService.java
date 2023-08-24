package com.example.prog4.service;

import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.employee.EmployeeRepository;
import com.example.prog4.repository.employee.entity.Employee;
import com.example.prog4.service.cnapsService.CnapsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomEmployeeService {
  private  EmployeeRepository repository;
  private CnapsService cnapsNumberService;

  public Employee getEmployeeWithCnaps(String id) {
    Employee employee = repository.findById(id)
      .orElseThrow(() -> new NotFoundException("Not found id=" + id));

    // Fetch the CNAPS number for this employee
    com.example.prog4.repository.cnaps.cnapsEntity.Employee cnapsNumberEntity = cnapsNumberService.getCnapsNumberByEmployeeId(id);

    // Replace the CNAPS number in the employee object
    employee.setCnaps(cnapsNumberEntity.getCnaps());

    return employee;
  }
}
