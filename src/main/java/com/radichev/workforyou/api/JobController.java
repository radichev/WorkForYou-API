package com.radichev.workforyou.api;

import com.radichev.workforyou.service.JobService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/jobs")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
}
