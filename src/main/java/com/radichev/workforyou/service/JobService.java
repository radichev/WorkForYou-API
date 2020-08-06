package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Job;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;
import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBuyBindingModel;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobScheduledTaskDto;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobService {

    JobViewModel addJob(JobBindingModel jobBindingModel, String userId, MultipartFile file);

    List<JobViewModel> findAllJobsByUserId(String userId);

    Job findJobById(String jobId);

    List<JobViewModel> findFiveJobsInGivenSubSphere(String subSphereName, PageRequest pageRequest);

    Page<JobViewModel> findAllJobsBySubSphereId(String subSphereId, PageRequest pageRequest);

    void buyJob(JobBuyBindingModel jobBuyBindingModel);

    List<JobViewModel> findJobsBoughtByUserId(String userId);

    String uploadJobImage(String userId, String jobTitle, MultipartFile file);

    void deleteJobById(String jobId);

    List<JobScheduledTaskDto> findAllJobs();
}
