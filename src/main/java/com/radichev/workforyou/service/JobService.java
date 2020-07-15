package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.job.jobBindingModel.JobBindingModel;

public interface JobService {

    void addJob(JobBindingModel jobBindingModel, String userId);
}
