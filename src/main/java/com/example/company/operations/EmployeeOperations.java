//package com.example.company.operations;
//
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class EmployeeOperations {
////    private final EntityManager entityManager;
//
////    public EmployeeOperations(EntityManager entityManager) {
////        this.entityManager = entityManager;
////    }
//    public <Employee> List<Employee> getEmployeesWithSalaryAboveThreshold(double threshold) {
//        return null;
//    }
////        Query query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.empSalary > :threshold");
////        query.setParameter("threshold", threshold);
////        return query.getResultList();
////    }
//}
package com.example.company.operations;

import com.example.company.model.Employee;
import com.example.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeOperations {

    private final EmployeeRepository employeeRepository;

    // Constructor injection with @Autowired
    @Autowired
    public EmployeeOperations(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeesWithSalaryAboveThreshold(double threshold) {
        return employeeRepository.findByEmpSalaryGreaterThan(threshold);
    }
}
