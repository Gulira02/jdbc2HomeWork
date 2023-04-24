package org.example.dao.impl;

import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Job;
import org.example.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    public void createEmployee() {
        String sql = "create table employees (id serial primary key ," +
                "first_name varchar," +
                "last_name varchar," +
                "age int," +
                " email varchar ," +
                "job_id int references jobs(id))";
        System.out.println("Connected to table");
        try( Connection connection=Util.connectionToDatabase()){
            Statement statement= connection.createStatement();
            statement.executeUpdate(sql);
    }
    catch (SQLException e){
        System.out.println(e.getMessage());
    }}

    public void addEmployee(Employee employee) {
        try(Connection connection=Util.connectionToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employees (first_name,last_name,age, email,job_id ) VALUES (?, ?, ?,?,?)");){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void dropTable() {
        String sql="drop table if exists employees";
        try(Connection connection=Util.connectionToDatabase()){
            Statement statement= connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void cleanTable() {
        String sql="truncate table  employees ";
        try(Connection connection =Util.connectionToDatabase()){
            Statement statement= connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void updateEmployee(Long id, Employee employee) {
        String qeur = " update employees set first_name = ?, last_name = ?, age = ?, email = ?, job_id = ?" +
                " where id = ?";
        try (Connection connection=Util.connectionToDatabase();
                PreparedStatement preparedStatement = connection.prepareStatement(qeur)){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5, employee.getJobId());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Employee> getAllEmployees() {
        List<Employee>empl=new ArrayList<>();
        String sql="select*from employess";
        try(Connection connection=Util.connectionToDatabase()){
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                resultSet.getLong("id");
                resultSet.getString("first_name");
                resultSet.getString("last_name");
                resultSet.getInt("age");
                resultSet.getString("email");
                resultSet.getInt("job_id");

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return empl;

    }

    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String sql = "SELECT * FROM employees WHERE email = ?";
        try(Connection connection=Util.connectionToDatabase();
        PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setString(1,email);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
               employee.setId( resultSet.getLong("id"));
               employee.setFirstName( resultSet.getString("first_name"));
               employee.setLastName( resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
        }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return  employee ;
    }


    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job> getAll = new HashMap<>();
        String sql1 = "select * from employees e join jobs j ON e.job_id = j.id where e.id = ?";
        try (Connection connection = Util.connectionToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                getAll.put(new Employee(
                                resultSet.getLong("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getInt("age"),
                                resultSet.getString("email"),
                                resultSet.getInt(resultSet.getInt("job_id"))
                        ), new Job(
                                resultSet.getLong("id"),
                                resultSet.getString("position"),
                                resultSet.getString("profession"),
                                resultSet.getString("description"),
                                resultSet.getInt("experience"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting employee by id: " + e.getMessage());
        }
        return getAll;
    }
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employees = new ArrayList<>();
        String query ="select * from employees join job j on employees.job_id = j.id where j.position = ?";
        try(Connection connection=Util.connectionToDatabase();
        PreparedStatement preparedStatement= connection.prepareStatement(query)){
            preparedStatement.setString(1,position);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Employee employee=new Employee();
                employee.setId( resultSet.getLong("id"));
                employee.setFirstName( resultSet.getString("first_name"));
                employee.setLastName( resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }
}
