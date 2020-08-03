package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBuyBindingModel;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;
import com.radichev.workforyou.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/jobs")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<JobViewModel>> getAllJobs(@PathVariable String userId) {
        return ResponseEntity
                .ok()
                .body(this.jobService.findAllJobsByUserId(userId));
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobViewModel> getJobById(@PathVariable String jobId) {
        return ResponseEntity
                .ok()
                .body(this.jobService.findJobById(jobId));
    }

    @PostMapping("/buy/{jobId}")
    public ResponseEntity<Void> buyJob(@Valid @RequestBody JobBuyBindingModel jobBuyBindingModel) {
        this.jobService.buyJob(jobBuyBindingModel);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Void> addJob(@PathVariable String userId,
                                       @Valid @RequestBody JobBindingModel jobBindingModel,
                                       UriComponentsBuilder ucBuilder) {

        JobViewModel jobViewModel = this.jobService.addJob(jobBindingModel, userId);

        return ResponseEntity
                .created(ucBuilder.path("/jobs/{jobId}")
                        .buildAndExpand(jobViewModel.getId())
                        .toUri())
                        .build();
    }

    @GetMapping("/{userId}/bought")
    public ResponseEntity<List<JobViewModel>> getJobsBoughtByUserId(@PathVariable String userId) {
        return ResponseEntity
                .ok()
                .body(this.jobService.findJobsBoughtByUserId(userId));
    }

    @GetMapping("/sub-sphere/{subSphereName}")
    public ResponseEntity<List<JobViewModel>> findFiveJobsInGivenSubSphere(@PathVariable String subSphereName) {
        return ResponseEntity
                .ok()
                .body(this.jobService.findFiveJobsInGivenSubSphere(subSphereName, PageRequest.of(0, 5)));
    }

    @GetMapping(value = "/sub-sphere/{subSphereId}/all",
            params = {"page", "size"})
    public ResponseEntity<Page<JobViewModel>> findAllJobsInGivenSubSphere(@PathVariable String subSphereId,
                                                                          @RequestParam("page") int page,
                                                                          @RequestParam("size") int size) {

        return ResponseEntity
                .ok()
                .body(this.jobService.findAllJobsBySubSphereId(subSphereId, PageRequest.of(page, size)));
    }
}
