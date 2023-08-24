package com.example.prog4.service;

import com.example.prog4.repository.entity.Employee;
import com.example.prog4.service.cnapsService.CnapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade {
  private final CustomEmployeeService customEmployeeService;
  private final CnapsService cnapsNumberService;

  @Autowired
  public EmployeeFacadeImpl(
    CustomEmployeeService customEmployeeService,
    CnapsService cnapsNumberService
  ) {
    this.customEmployeeService = customEmployeeService;
    this.cnapsNumberService = cnapsNumberService;
  }

  @Override
  public Employee getEmployeeWithCnaps(String id) {
    Employee employee = customEmployeeService.getEmployeeWithCnaps(id);

    // Fetch the CNAPS number for this employee
    String cnapsNumber = cnapsNumberService.getCnapsNumberByEmployeeId(id).getCnaps();

    // Set the CNAPS number in the employee object
    employee.setCnaps(cnapsNumber);

    return employee;
  }
}
