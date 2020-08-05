package com.radichev.workforyou.model.viewModels.jobViewModels;

import com.radichev.workforyou.model.dtos.WorkSphereDto.SubSphereDto;
import com.radichev.workforyou.model.dtos.WorkSphereDto.WorkSphereDto;

import java.math.BigDecimal;

public class JobViewModel {
    private String id;
    private String jobTitle;
    private WorkSphereViewModel workSphere;
    private SubSphereDto subSphere;
    private int deliveryTime;
    private BigDecimal price;
    private String description;
    private String jobPicture;
    private UserProfileDetailsJobViewModel userProfileDetails;

    public JobViewModel() {
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

    public WorkSphereViewModel getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(WorkSphereViewModel workSphere) {
        this.workSphere = workSphere;
    }

    public SubSphereDto getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(SubSphereDto subSphere) {
        this.subSphere = subSphere;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobPicture() {
        return jobPicture;
    }

    public void setJobPicture(String jobPicture) {
        this.jobPicture = jobPicture;
    }

    public UserProfileDetailsJobViewModel getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(UserProfileDetailsJobViewModel userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }
}
