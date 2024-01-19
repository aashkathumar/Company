package com.example.company.controller;

import com.example.company.model.Employee;
import com.example.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get")
    public Employee getEmployeeById(@RequestParam(name = "empId") int empId) {
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{empId}")
    public void deleteEmployee(int empId) {
        employeeService.deleteEmployee(empId);
    }

    @GetMapping("/salaryAbove/{threshold}")
    public List<Employee> getEmployeesWithSalaryAboveThreshold(@PathVariable double threshold) {
        return employeeService.getEmployeesWithSalaryAboveThreshold(threshold);
    }
    @GetMapping("/byPosition/{position}")
    public List<Employee> getEmployeeByPosition(@PathVariable String position) {
        return employeeService.getEmployeeByPosition(position);
    }
    @GetMapping("/byDate/{empJoinDate}")
    public List<Employee> getEmployeeWithDateOfJoining(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date empJoinDate) {
        return employeeService.getEmployeeWithJoiningDateAbove(empJoinDate);
    }
    @GetMapping("/by-position")
    public List<Employee> getEmployeesByPositionSorted(
            @RequestParam String position,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return employeeService.getEmployeesByPositionSorted(position, sortOrder);
    }
}
