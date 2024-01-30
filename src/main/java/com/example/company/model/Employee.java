package com.example.company.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;


@Entity(name = "Employee")
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "emp_id")
    private long id;

    @NotBlank(message = "Please enter your name")
    @Size(max = 255, message = "Name cannot be more than 255 characters")
    @Column(name = "emp_name", nullable = false)
    private String empName;

    @NotBlank(message = "Please enter your designation")
    @Column(name = "emp_position", nullable = false)
    private String empPosition;

    @NotBlank(message = "Please enter salary")
    @Column(name = "emp_salary", precision = 15, scale = 2, nullable = false)
    private BigDecimal empSalary;

    @NotBlank(message = "Please enter your date of joining")
    @Column(name = "emp_joindate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date empJoinDate;

    @Version
    @Column(name = "version")
    private long version;

    public Employee() {
    }

    public Employee(long id, String empName, String empPosition, BigDecimal empSalary, Date empJoinDate, long version) {
        this.id = id;
        this.empName = empName;
        this.empPosition = empPosition;
        this.empSalary = empSalary;
        this.empJoinDate = empJoinDate;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public BigDecimal getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(BigDecimal empSalary) {
        this.empSalary = empSalary;
    }

    public Date getEmpJoinDate() {
        return empJoinDate;
    }

    public void setEmpJoinDate(Date empJoinDate) {
        this.empJoinDate = empJoinDate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", empPosition='" + empPosition + '\'' +
                ", empSalary=" + empSalary +
                ", empJoinDate=" + empJoinDate +
                ", version=" + version +
                '}';
    }
}
