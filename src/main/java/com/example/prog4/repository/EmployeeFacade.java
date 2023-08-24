package com.example.prog4.repository;

import com.example.prog4.repository.employee.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeFacade {
  Employee getEmployeeWithCnaps(String id);
}
