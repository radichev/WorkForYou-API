package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;

import java.util.List;

public interface JobService {

    JobViewModel addJob(JobBindingModel jobBindingModel, String userId);

    List<JobViewModel> findAllJobsByUserId(String userId);

    JobViewModel findJobById(String jobId);

    List<JobViewModel> findFiveJobsInWebProgramming();
}
