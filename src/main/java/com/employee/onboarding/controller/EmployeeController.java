package com.employee.onboarding.controller;

import com.employee.onboarding.model.Employee;
import com.employee.onboarding.service.EmployeeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee registerEmployee(@RequestBody Employee employee) {
        return service.registerEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}/approve")
    public Employee approveEmployee(@PathVariable Long id) {
        return service.approveEmployee(id);
    }

    @PutMapping("/{id}/reject")
    public Employee rejectEmployee(@PathVariable Long id) {
        return service.rejectEmployee(id);
    }
}