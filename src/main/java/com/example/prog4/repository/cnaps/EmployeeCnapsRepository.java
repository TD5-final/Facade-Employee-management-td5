package com.example.prog4.repository.cnaps;

import com.example.prog4.repository.cnaps.cnapsEntity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCnapsRepository extends JpaRepository<Employee, String> {
   Employee findByEmployeeId(String employeeId);
}
