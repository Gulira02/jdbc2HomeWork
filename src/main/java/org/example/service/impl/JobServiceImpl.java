package org.example.service.impl;

import org.example.dao.JobDao;
import org.example.dao.impl.JobDaoImpl;
import org.example.model.Job;
import org.example.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    JobDao jobDao=new JobDaoImpl();

    public void createJobTable() {
        jobDao.createJobTable();

    }

    public void addJob(Job job) {
        jobDao.addJob(job);

    }

    public Job getJobById(Long jobId) {

        return jobDao.getJobById(jobId);
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();

    }
}
