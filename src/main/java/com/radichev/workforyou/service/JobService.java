package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBuyBindingModel;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface JobService {

    JobViewModel addJob(JobBindingModel jobBindingModel, String userId);

    List<JobViewModel> findAllJobsByUserId(String userId);

    JobViewModel findJobById(String jobId);

    List<JobViewModel> findFiveJobsInGivenSubSphere(String subSphereName, PageRequest pageRequest);

    Page<JobViewModel> findAllJobsBySubSphereId(String subSphereId, PageRequest pageRequest);

    void buyJob(JobBuyBindingModel jobBuyBindingModel);
}
