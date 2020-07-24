package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Job;
import com.radichev.workforyou.domain.entity.SubSphere;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.domain.entity.WorkSphere;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;
import com.radichev.workforyou.repository.JobRepository;
import com.radichev.workforyou.service.JobService;
import com.radichev.workforyou.service.SubSphereService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import com.radichev.workforyou.service.WorkSphereService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private static final String WEB_PROGRAMMING_SUBSPHERE = "Web programming";

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;
    private final WorkSphereService workSphereService;
    private final SubSphereService subSphereService;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, UserProfileDetailsService userProfileDetailsService, WorkSphereService workSphereService, SubSphereService subSphereService) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
        this.workSphereService = workSphereService;
        this.subSphereService = subSphereService;
    }


    @Override
    public JobViewModel addJob(JobBindingModel jobBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        WorkSphere workSphere = this.workSphereService.findWorkSphereById(jobBindingModel.getWorkSphere().getId());
        SubSphere subSphere = this.subSphereService.findSubSphereById(jobBindingModel.getWorkSphere().getSubSphere().getId());

        Job job = this.modelMapper.map(jobBindingModel, Job.class);
        job.setUserProfileDetails(userProfileDetails);
        job.setWorkSphere(workSphere);
        job.setSubSphere(subSphere);

        return this.modelMapper.map(this.jobRepository.save(job), JobViewModel.class);
    }

    @Override
    public List<JobViewModel> findAllJobsByUserId(String userId) {
        return this.jobRepository.findAllByUserId(userId).stream()
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
    public JobViewModel findJobById(String jobId) {
        return this.modelMapper.map(this.jobRepository.findById(jobId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(String.format("Job not found with %s id.", jobId))), JobViewModel.class);
    }

    @Override
    public List<JobViewModel> findFiveJobsInWebProgramming() {
        return this.jobRepository.findFiveJobsBySubSphere(WEB_PROGRAMMING_SUBSPHERE, PageRequest.of(0, 5))
                .stream()
                .map(job -> this.modelMapper.map(job, JobViewModel.class))
                .collect(Collectors.toList());
    }
}
