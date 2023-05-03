package com.milind.jobmanagement.controller;

import com.milind.jobmanagement.entity.Job;
import com.milind.jobmanagement.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    @Autowired
    JobService jobService;

    @PostMapping("/addJob")
    public String addJob(@Valid @RequestBody Job job) {
        return jobService.addJob(job);
    }

    @GetMapping("/getJob/{id}")
    public ResponseEntity<Job> getJob(@PathVariable long id) {
        Job job = jobService.getJobFromJobId(id);

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<List <Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();

        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping("/updateJobInfo")
    public String updateJobInfo(@RequestBody Job job) {
        Boolean updated = jobService.updateJobInfo(job);
        if(updated) {
            return "Job with job id " + job.getId() + " updated";
        }
        else {
            return "No such job present";
        }
    }

    @PostMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable long id) {
        jobService.deleteJobWithJobId(id);
        return "Job Removed";
    }

    @GetMapping("/searchJob/{searchText}")
    public List<Job> searchJob(@PathVariable String searchText) {
        System.out.println("--> " + searchText);
        return jobService.searchJob(searchText);
    }
}
