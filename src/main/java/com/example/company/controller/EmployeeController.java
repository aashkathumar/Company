package com.example.company.controller;

import com.example.company.model.Employee;
import com.example.company.repository.EmployeeRepository;
import com.example.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
//        return employeeRepository.findAll();
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
    @GetMapping("/by-name")
    public List<Employee> getEmployeesByPositionSorted() {
        return employeeService.getEmployeeSortedByName();
    }
    @GetMapping("/emp/{position}")
    public Page<Employee> getEmployeesByPositionPaginated(
            @PathVariable String position,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeesByPositionPaginated(position, page, size);
    }
    @GetMapping("/emp")
    public Page<Employee> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }
//    @GetMapping("/emp/criteria")
//    public Page<Employee> getEmployeesByCriteria(
//            @PathVariable String position,
//            @PathVariable String criteria,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
////            List <Employee> list = employeeService.getEmployeeByPosition(position);
//        Sort sort = Sort.by(criteria).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        // Fetch employees by position from your service
//        List<Employee> employees = employeeService.getEmployeeByPosition(position);
//
//        // Sort the employees based on the specified criteria
//        employees.sort(Comparator.comparing(Employee::getEmpName));
//
//        // Create a Page object using the sorted list and pageable information
//        int start = (int) pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), employees.size());
//        return new PageImpl<>(employees.subList(start, end), pageable, employees.size());
//    }


}