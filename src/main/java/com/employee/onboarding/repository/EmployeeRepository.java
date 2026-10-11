package com.employee.onboarding.repository;

import com.employee.onboarding.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

List<Employee> findByDepartment(String department);


}
