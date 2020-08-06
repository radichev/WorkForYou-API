package com.radichev.workforyou.model.viewModels.jobViewModels;

import java.time.LocalDate;

public class JobScheduledTaskDto {
    private String id;
    private String jobTitle;
    private LocalDate createdDate;

    public JobScheduledTaskDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
