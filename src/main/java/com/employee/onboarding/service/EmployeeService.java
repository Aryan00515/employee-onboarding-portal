package com.employee.onboarding.service;

import com.employee.onboarding.model.Employee;
import com.employee.onboarding.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

private final EmployeeRepository repository;

public EmployeeService(EmployeeRepository repository) {
    this.repository = repository;
}

public Employee registerEmployee(Employee employee) {
    employee.setStatus("PENDING");
    return repository.save(employee);
}

public List<Employee> getAllEmployees() {
    return repository.findAll();
}

public Employee getEmployeeById(Long id) {
    return repository.findById(id).orElse(null);
}

public List<Employee> getEmployeesByDepartment(String department) {
    return repository.findByDepartment(department);
}

public Employee approveEmployee(Long id) {
    Employee employee = repository.findById(id).orElse(null);

    if (employee != null) {
        employee.setStatus("APPROVED");
        return repository.save(employee);
    }

    return null;
}

public Employee rejectEmployee(Long id) {
    Employee employee = repository.findById(id).orElse(null);

    if (employee != null) {
        employee.setStatus("REJECTED");
        return repository.save(employee);
    }

    return null;
}


}
