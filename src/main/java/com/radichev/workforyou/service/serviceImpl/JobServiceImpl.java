package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.bucket.BucketName;
import com.radichev.workforyou.domain.entity.Job;
import com.radichev.workforyou.domain.entity.SubSphere;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.domain.entity.WorkSphere;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBuyBindingModel;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobScheduledTaskDto;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;
import com.radichev.workforyou.repository.JobRepository;
import com.radichev.workforyou.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.http.entity.ContentType.*;

@Service
public class JobServiceImpl implements JobService {
    private static final String AWS_S3_DEFAULT_URL = "https://workforyou-images.s3.amazonaws.com/";
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;
    private final WorkSphereService workSphereService;
    private final SubSphereService subSphereService;
    private final FileStoreService fileStoreService;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, UserProfileDetailsService userProfileDetailsService, WorkSphereService workSphereService, SubSphereService subSphereService, FileStoreService fileStoreService) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
        this.workSphereService = workSphereService;
        this.subSphereService = subSphereService;
        this.fileStoreService = fileStoreService;
    }


    @Override
    public JobViewModel addJob(JobBindingModel jobBindingModel, String userId, MultipartFile file) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        WorkSphere workSphere = this.workSphereService.findWorkSphereById(jobBindingModel.getWorkSphere().getId());
        SubSphere subSphere = this.subSphereService.findSubSphereById(jobBindingModel.getWorkSphere().getSubSphere().getId());

        Job job = this.modelMapper.map(jobBindingModel, Job.class);
        job.setUserProfileDetails(userProfileDetails);
        job.setWorkSphere(workSphere);
        job.setSubSphere(subSphere);
        job.setJobPicture(uploadJobImage(userId, jobBindingModel.getJobTitle(), file));


        return this.modelMapper.map(this.jobRepository.save(job), JobViewModel.class);
    }

    @Override
    public List<JobViewModel> findAllJobsByUserId(String userId) {
        return this.jobRepository.findAllByUserId(userId)
                .stream()
                .map(job -> {
                    JobViewModel jobViewModel = this.modelMapper.map(job, JobViewModel.class);
                    jobViewModel.getUserProfileDetails().setCountry(job.getUserProfileDetails().getCountry().getCountry());
                    jobViewModel.getUserProfileDetails().setUsername(job.getUserProfileDetails().getUser().getUsername());
                    jobViewModel.getUserProfileDetails().setUserId(job.getUserProfileDetails().getUser().getId());

                    return jobViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Job findJobById(String jobId) {
        return this.jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Job not found with %s id.", jobId)));
    }

    @Override
    public List<JobViewModel> findFiveJobsInGivenSubSphere(String subSphereName, PageRequest pageRequest) {
        return this.jobRepository.findFiveJobsBySubSphere(subSphereName, pageRequest)
                .stream()
                .map(job -> this.modelMapper.map(job, JobViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<JobViewModel> findAllJobsBySubSphereId(String subSphereId, PageRequest pageRequest) {
        return this.jobRepository.findAllJobsBySubSphereId(subSphereId, pageRequest)
                .map(job -> this.modelMapper.map(job, JobViewModel.class));
    }

    @Override
    public void buyJob(JobBuyBindingModel jobBuyBindingModel) {
        Job job = findJobById(jobBuyBindingModel.getJobId());

        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(jobBuyBindingModel.getUserId());

        job.getBoughtByUser().add(userProfileDetails);

        this.jobRepository.save(job);
    }

    @Override
    public List<JobViewModel> findJobsBoughtByUserId(String userId) {
        return this.jobRepository.findJobsBoughtByUserId(userId)
                .stream()
                .map(job -> this.modelMapper.map(job, JobViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String uploadJobImage(String userId, String jobTitle, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image");
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userId, jobTitle);
        String fileName = String.format("%s", file.getOriginalFilename());

        String key = String.format("%s/%s/%s", userId, jobTitle, fileName);
        String url = String.format("%s%s", AWS_S3_DEFAULT_URL, key);

        try {
            this.fileStoreService.save(path, fileName, Optional.of(metadata), file.getInputStream());
            return url;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void deleteJobById(String jobId) {
        Job job = findJobById(jobId);
        job.setDeleted(true);
        job.setDeletedOn(LocalDate.now());

        this.jobRepository.save(job);
    }

    @Override
    public List<JobScheduledTaskDto> findAllJobs() {
        return this.jobRepository.findAll()
                .stream()
                .map(job -> this.modelMapper.map(job, JobScheduledTaskDto.class))
                .collect(Collectors.toList());
    }
}
