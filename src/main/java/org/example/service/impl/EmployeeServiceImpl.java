package org.example.service.impl;

import org.example.dao.EmployeeDao;
import org.example.dao.impl.EmployeeDaoImpl;
import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.EmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao=new EmployeeDaoImpl();

    public void createEmployee() {
        employeeDao.createEmployee();

    }

    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

    }

    public void dropTable() {
        employeeDao.dropTable();

    }

    public void cleanTable() {
        employeeDao.cleanTable();

    }

    public void updateEmployee(Long id, Employee employee) {
        employeeDao.updateEmployee(id,employee);

    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee findByEmail(String email) {

        return employeeDao.findByEmail(email);
    }

    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    public List<Employee> getEmployeeByPosition(String position) {

        return employeeDao.getEmployeeByPosition(position);
    }
}
