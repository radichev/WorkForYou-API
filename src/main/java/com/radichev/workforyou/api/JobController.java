package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/jobs")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Void> addJob(@PathVariable String userId, @Valid @RequestBody JobBindingModel jobBindingModel){
        this.jobService.addJob(jobBindingModel, userId);
        return ResponseEntity.ok().build();
    }
}
