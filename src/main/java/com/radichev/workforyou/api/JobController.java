package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBuyBindingModel;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;
import com.radichev.workforyou.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/jobs")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {
    private final JobService jobService;
    private final ModelMapper modelMapper;

    public JobController(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<JobViewModel>> getAllJobsByUserId(@PathVariable String userId) {
        return ResponseEntity
                .ok()
                .body(this.jobService.findAllJobsByUserId(userId));
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobViewModel> getJobById(@PathVariable String jobId) {
        return ResponseEntity
                .ok()
                .body(this.modelMapper.map(this.jobService.findJobById(jobId), JobViewModel.class));
    }

    @PostMapping("/buy/{jobId}")
    public ResponseEntity<Void> buyJob(@Valid @RequestBody JobBuyBindingModel jobBuyBindingModel) {
        this.jobService.buyJob(jobBuyBindingModel);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping(value = "/add/{userId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> addJob(@PathVariable String userId,
                                       @RequestPart("job") @Valid JobBindingModel jobBindingModel,
                                       @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
                                       UriComponentsBuilder ucBuilder) {

        JobViewModel jobViewModel = this.jobService.addJob(jobBindingModel, userId, file);

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

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJobById(@PathVariable String jobId) {

        this.jobService.deleteJobById(jobId);

        return ResponseEntity
                .ok()
                .build();
    }
}
