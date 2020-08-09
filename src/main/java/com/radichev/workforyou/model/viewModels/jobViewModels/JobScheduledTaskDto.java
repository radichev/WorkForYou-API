package com.radichev.workforyou.model.viewModels.jobViewModels;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.viewModels.userProfileDetails.UserProfileDetailsViewModel;

import java.time.LocalDate;
import java.util.Set;

public class JobScheduledTaskDto {
    private String id;
    private String jobTitle;
    private LocalDate createdDate;
    private Set<UserProfileDetailsViewModel> boughtByUser;

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

    public Set<UserProfileDetailsViewModel> getBoughtByUser() {
        return boughtByUser;
    }

    public void setBoughtByUser(Set<UserProfileDetailsViewModel> boughtByUser) {
        this.boughtByUser = boughtByUser;
    }
}
