package com.example.company.repository;

import com.example.company.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmpSalaryGreaterThan(double threshold);
    List<Employee> findByEmpPosition(String position);
    List<Employee> findByEmpJoinDateAfter(Date empJoinDate);
    List<Employee> findByEmpName(String empName, Sort sort);
    Page<Employee> findByEmpPosition(String position, Pageable pageable);
    Page<Employee> findAll(Pageable pageable);
}