package com.milind.jobmanagement.service;

import com.milind.jobmanagement.controller.JobController;
import com.milind.jobmanagement.entity.Job;
import com.milind.jobmanagement.repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class JobService {
    @Autowired
    JobRepository jobRepository;

    public String addJob(Job job) {
        jobRepository.save(job);
        return "Job " + job.getCompanyName() + " Saved";
    }

    public Job getJobFromJobId(long id) {
        return jobRepository.findById(id).get();
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public boolean updateJobInfo(Job job) {
        try {
            Job jobToUpdate = jobRepository.findById(job.getId()).get();
            jobToUpdate = job;
            jobRepository.save(jobToUpdate);

            return true;
        }
        catch (NullPointerException e) {
            System.out.println("No such Job Present");

            return false;
        }
    }

    public void deleteJobWithJobId(long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> searchJob(String searchText) {
        Set<Job> jobs = new HashSet<>();
        List<Job> allJobs = jobRepository.findAll();
        for(Job job: allJobs) {
            try {
                if(job.getCompanyName().equalsIgnoreCase(searchText)
                        || job.getEmployerName().equalsIgnoreCase(searchText)
                        || job.getTitle().equalsIgnoreCase(searchText)) {
                    jobs.add(job);
                }
            }
            finally {
            System.out.println("ne");
            }
        }

        return new ArrayList<>(jobs);
    }
}
