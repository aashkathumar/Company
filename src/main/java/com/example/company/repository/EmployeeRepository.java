package com.example.company.repository;

import com.example.company.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Query("SELECT e FROM Employee e WHERE e.empSalary > :threshold AND e.empJoinDate >= CAST(:sixMonthsAgo AS java.sql.Timestamp)")
    List<Employee> findEmployeesAboveThresholdAndJoinDate(@Param("threshold") BigDecimal threshold,
                                                          @Param("sixMonthsAgo") LocalDate sixMonthsAgo);

    @Query("SELECT e FROM Employee e WHERE e.empSalary > :threshold AND e.id <= :id")
    List<Employee> findEmployeeAboveThresholdAndId(@Param("threshold") BigDecimal threshold,
                                                   @Param("id") long id);
}