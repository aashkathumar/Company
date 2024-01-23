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

    //Get list of Employees above a particular threshold
    @GetMapping("/by-salary/{threshold}")
    public List<Employee> getEmployeesWithSalaryAboveThreshold(@PathVariable double threshold) {
        return employeeService.getEmployeesWithSalaryAboveThreshold(threshold);
    }

    @GetMapping("/by-position/{position}")
    public List<Employee> getEmployeeByPosition(@PathVariable String position) {
        return employeeService.getEmployeeByPosition(position);
    }

    @GetMapping("/byDate/{empJoinDate}")
    public List<Employee> getEmployeeWithDateOfJoining(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date empJoinDate) {
        return employeeService.getEmployeeWithJoiningDateAbove(empJoinDate);
    }

    //Get List of Employees sorted on the basis of a particular criteria
    @GetMapping("/by-name")
    public List<Employee> getEmployeesByPositionSorted() {
        return employeeService.getEmployeeSortedByName();
    }

    @GetMapping("/by-salary")
    public List<Employee> getEmployeesBySalarySorted() {
        return employeeService.getEmployeeSortedBySalary();
    }

    @GetMapping("/by-date")
    public List<Employee> getEmployeesByDateSorted() {
        return employeeService.getEmployeeSortedByDate();
    }

    //Get Paginated data of Employees based on a particular criteria
    @GetMapping("/page-all")
    public Page<Employee> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }
    @GetMapping("/page-position/{position}")
    public Page<Employee> getEmployeesByPositionPaginated(
            @PathVariable String position,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeesByPositionPaginated(position, page, size);
    }
    @GetMapping("/page-name")
    public Page<Employee> getEmployeesByNamePaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeesByNamePaginated(page, size);
        //Pageable pageable = PageRequest.of(page, size, Sort.by("empName").ascending());
        //return employeeRepository.findAll(pageable);
    }
    @GetMapping("/page-date")
    public Page<Employee> getEmployeesByDatePaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeesByNamePaginated(page, size);
        //Pageable pageable = PageRequest.of(page, size, Sort.by("empName").ascending());
        //return employeeRepository.findAll(pageable);
    }
    @GetMapping("/page-salary")
    public Page<Employee> getEmployeesBySalaryPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeesByNamePaginated(page, size);
        //Pageable pageable = PageRequest.of(page, size, Sort.by("empName").ascending());
        //return employeeRepository.findAll(pageable);
    }
    @GetMapping("/page")
    public Page<Employee> getEmployeesPaginatedByCriteria(
          @RequestParam(defaultValue = "empName") String criteria,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeesByCriteriaPaginated(page, size, criteria);
    }
}