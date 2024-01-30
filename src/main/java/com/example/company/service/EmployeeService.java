package com.example.company.service;

import com.example.company.model.Employee;
import com.example.company.operations.EmployeeOperations;
import com.example.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public List<Employee> getEmployeeSortedByName() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "empName"));
    }
    public List<Employee> getEmployeeSortedBySalary() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC,"empSalary"));
    }
    public List<Employee> getEmployeeSortedByDate() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "empJoinDate"));
    }
    public Page<Employee> getEmployeesByPositionPaginated(String position, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findByEmpPosition(position, pageable);
    }
    public Page<Employee> getEmployeesByNamePaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("empName").ascending());
        return employeeRepository.findAll(pageable);
    }
    public Page<Employee> getEmployeesByDatePaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("empJoinDate").ascending());
        return employeeRepository.findAll(pageable);
    }
    public Page<Employee> getEmployeesBySalaryPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("empSalary").ascending());
        return employeeRepository.findAll(pageable);
    }
    public Page<Employee> getEmployeesByCriteriaPaginated(int page, int size, String criteria) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(criteria).ascending());
//        return employeeRepository.findAll(pageable);
        Sort sort = Sort.by(criteria).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }
    public List<Employee> findEmployeesAboveThresholdAndJoinDate(BigDecimal threshold) {
        LocalDate twoMonthsAgo = LocalDate.now().minusMonths(2);
        return employeeRepository.findEmployeesAboveThresholdAndJoinDate(threshold, twoMonthsAgo);
    }
    public List<Employee> findEmployeeAboveThresholdAndId(BigDecimal threshold, long id) {
        return employeeRepository.findEmployeeAboveThresholdAndId(threshold, id);
    }
//    @Transactional
//    public Employee updateEmployee(long empId, Employee updatedEmployee) {
//        try {
//            Employee existingEmployee = employeeRepository.findById(updatedEmployee.getEmpId()).orElse(null);
//
//            if (existingEmployee != null) {
//                // Apply changes to the existing employee
//                existingEmployee.setEmpName(updatedEmployee.getEmpName());
//                existingEmployee.setEmpPosition(updatedEmployee.getEmpPosition());
//                existingEmployee.setEmpSalary(updatedEmployee.getEmpSalary());
//
//                // Save the updated employee
//                return employeeRepository.save(existingEmployee);
//            } else {
//                // Employee not found
//                return null;
//            }
//        } catch (OptimisticLockingFailureException e) {
//            // Handle concurrency conflict
//            // You might inform the user or retry the operation
//            // For example, you can throw a custom exception indicating a concurrency conflict
//            throw new ConcurrencyConflictException("Concurrency conflict during employee update", e);
//        }
//    }
    @Transactional
    public Employee updateEmployee(long empId, Employee updatedEmployee) {

        Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(null);
        // Apply changes to the existing employee
        existingEmployee.setEmpName(updatedEmployee.getEmpName());
        existingEmployee.setEmpPosition(updatedEmployee.getEmpPosition());
        existingEmployee.setEmpSalary(updatedEmployee.getEmpSalary());

        // Save the updated employee
        return employeeRepository.save(existingEmployee);
    }
}