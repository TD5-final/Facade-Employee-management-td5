package com.example.prog4;

import com.example.prog4.repository.employee.EmployeeRepository;
import com.example.prog4.repository.cnaps.EmployeeCnapsRepository;
import com.example.prog4.repository.employee.entity.Employee;
import com.example.prog4.repository.employee.entity.enums.Csp;
import com.example.prog4.repository.employee.entity.enums.Sex;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class MultippleDbTest {
  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeeCnapsRepository cnapsEmployeeRepository;

  @Test
  @Transactional("employeeTransactionManager")
  public void whenCreatingEmployee_thenCreated() {
    Employee employee = new Employee();
    employee.setFirstName("John");
    employee.setLastName("Doe");
    employee.setAddress("Ivandry");
    employee.setRegistrationNumber("YourRegistrationNumberHere");
    employee.setPersonalEmail("john.doe@example.com");
    employee.setCin("123456"); // Replace with the actual Cin
    employee.setCnaps("ABC123"); // Replace with the actual Cnaps
    employee.setChildrenNumber(0); // Replace with the actual children_number if needed
    employee.setBirthDate(LocalDate.parse("2000-01-01")); // Replace with the actual birth_date
    employee.setEntranceDate(LocalDate.now()); // Replace with the actual entrance_date
    employee.setSex(Sex.H); // Replace with Sex.MALE or Sex.FEMALE as appropriate
    employee.setCsp(Csp.AGRICULTURAL_WORKERS); // Replace with the actual csp, e.g., Csp.SOME_CSP
    employee.setImage(null); // Replace with the image if needed
    employee.setProfessionalEmail("john.doe@example.com");
    var employees = employeeRepository.save(employee);

    assertNotNull(employees);
  }

  @Test
  @Transactional("employeeTransactionManager")
  public void whenCreatingCnaps_thenCreated() {
    com.example.prog4.repository.cnaps.cnapsEntity.Employee employee = new com.example.prog4.repository.cnaps.cnapsEntity.Employee();
    employee.setFirstName("John");
    employee.setLastName("Doe");
    employee.setAddress("Ivandry");
    employee.setRegistrationNumber("YourRegistrationNumberHere");
    employee.setPersonalEmail("john.doe@example.com");
    employee.setEndToEndId("1245");
    employee.setCin("123456"); // Replace with the actual Cin
    employee.setCnaps("ABC123"); // Replace with the actual Cnaps
    employee.setChildrenNumber(0); // Replace with the actual children_number if needed
    employee.setBirthDate(LocalDate.parse("2000-01-01")); // Replace with the actual birth_date
    employee.setEntranceDate(LocalDate.now()); // Replace with the actual entrance_date
    employee.setSex(Sex.H); // Replace with Sex.MALE or Sex.FEMALE as appropriate
    employee.setCsp(Csp.AGRICULTURAL_WORKERS); // Replace with the actual csp, e.g., Csp.SOME_CSP
    employee.setImage(null); // Replace with the image if needed
    employee.setProfessionalEmail("john.doe@example.com");
    var employees = cnapsEmployeeRepository.save(employee);

    assertNotNull(employees);
  }
}
