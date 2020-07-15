package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Job;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.repository.JobRepository;
import com.radichev.workforyou.service.JobService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, UserProfileDetailsService userProfileDetailsService) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
    }


    @Override
    public void addJob(JobBindingModel jobBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        Job job = this.modelMapper.map(jobBindingModel, Job.class);
    }
}
