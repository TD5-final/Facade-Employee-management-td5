package com.example.prog4.service.cnapsService;

import com.example.prog4.repository.cnapsEntity.Employee;
import com.example.prog4.repository.cnapsRepository.EmployeeCnapsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class CnapsService {
  private  EmployeeCnapsRepository cnapsNumberRepository;

  @Autowired
  public CnapsService(EmployeeCnapsRepository cnapsNumberRepository) {
    this.cnapsNumberRepository = cnapsNumberRepository;
  }

  public Employee getCnapsNumberByEmployeeId(String employeeId) {
    return cnapsNumberRepository.findByEmployeeId(employeeId);
  }

  public void updateCnapsNumber(Employee cnapsNumberEntity) {
    cnapsNumberRepository.save(cnapsNumberEntity);
  }
}
