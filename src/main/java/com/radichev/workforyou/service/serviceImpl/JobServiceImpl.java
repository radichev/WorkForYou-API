package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Job;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.domain.entity.WorkSphere;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.repository.JobRepository;
import com.radichev.workforyou.service.JobService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import com.radichev.workforyou.service.WorkSphereService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;
    private final WorkSphereService workSphereService;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, UserProfileDetailsService userProfileDetailsService, WorkSphereService workSphereService) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
        this.workSphereService = workSphereService;
    }


    @Override
    public void addJob(JobBindingModel jobBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        WorkSphere workSphere = this.workSphereService.findWorkSphereById(jobBindingModel.getWorkSphere().getId());

        Job job = this.modelMapper.map(jobBindingModel, Job.class);
//        job.setUserProfileDetails(userProfileDetails);
        job.setWorkSphere(workSphere);
    }
}
