package com.example.company.repository;

import com.example.company.model.Employee;
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
    List<Employee> findByEmpPosition(String position, Sort sort);
}
