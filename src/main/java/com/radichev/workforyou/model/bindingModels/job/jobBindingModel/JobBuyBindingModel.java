package com.radichev.workforyou.model.bindingModels.job.jobBindingModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class JobBuyBindingModel {
    private String userId;
    private String jobId;

    public JobBuyBindingModel() {
    }

    @NotNull
    @NotBlank
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @NotNull
    @NotBlank
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
