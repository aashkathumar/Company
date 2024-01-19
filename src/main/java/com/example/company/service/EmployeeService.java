package com.example.company.service;

import com.example.company.model.Employee;
import com.example.company.operations.EmployeeOperations;
import com.example.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeOperations employeeOperations;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeOperations employeeOperations) {
        this.employeeRepository = employeeRepository;
        this.employeeOperations = employeeOperations;
    }
    //CRUD OPERATIONS
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(int empId) {
        Employee employee =  employeeRepository.findById((long) empId).orElse(null);
        System.out.println(employee);
        return employee;
    }
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(int empId) {
        employeeRepository.deleteById((long) empId);
    }
    public List<Employee> getEmployeesWithSalaryAboveThreshold(double threshold) {
        return employeeOperations.getEmployeesWithSalaryAboveThreshold(threshold);
    }
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeOperations.getEmployeeByPosition(position);
    }
    public List<Employee> getEmployeeWithJoiningDateAbove(Date empJoinDate) {
        return employeeOperations.getEmployeeWithDateOfJoiningAbove(empJoinDate);
    }
    public List<Employee> getEmployeesByPositionSorted(String position, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(Sort.Order.asc("empName")) : Sort.by(Sort.Order.desc("empName"));
        return employeeRepository.findByEmpPosition(position, sort);
    }
}
