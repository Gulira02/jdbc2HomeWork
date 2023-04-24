package org.example.dao.impl;

import org.example.dao.JobDao;
import org.example.model.Employee;
import org.example.model.Job;
import org.example.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JobDaoImpl implements JobDao {
    Connection connection= Util.connectionToDatabase();
    public void createJobTable() {
        String sql="create table jobs (" +
                "id serial primary key," +
                "position varchar," +
                "profession varchar," +
                "description varchar," +
                "experience int )";
        try(Statement statement= connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Connected to table jobs");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void addJob(Job job) {
       try(PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO jobs(position,profession,description,experience) VALUES(?,?,?,?)")){
           preparedStatement.setString(1,job.getPosition());
           preparedStatement.setString(2,job.getProfession());
           preparedStatement.setString(3,job.getDescription());
           preparedStatement.setInt(4,job.getExperience());
           preparedStatement.executeUpdate();
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
       }




    public Job getJobById(Long jobId) {
        Job job = new Job();
        String sql="select *from jobs where id=?";
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setLong(1,jobId);
            ResultSet  resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));

            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }


    public List<Job> sortByExperience(String ascOrDesc) {
        String sql="select * from jobs order by experience desc ";
        List<Job>jbs=new ArrayList<>();
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql);
         ResultSet resultSet=preparedStatement.executeQuery()){
            while (resultSet.next()){
                Job job=new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                jbs.add(job);
            }
        }
        catch (SQLException e){
        System.out.println(e.getMessage());
    }   return jbs;}

    public Job getJobByEmployeeId(Long employeeId) {
        String sql="select * from employees e join jobs j  on j.id=e.job_id where e.job_Id=?";
        Job job=new Job();
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                job.setId(resultSet.getLong("id"));


        }}catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }

    public void deleteDescriptionColumn() {
        String sql="ALTER TABLE jobs  DROP COLUMN description";
        try(Statement statement= connection.createStatement()){
            statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
