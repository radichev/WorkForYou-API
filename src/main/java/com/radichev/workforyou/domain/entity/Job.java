package com.radichev.workforyou.domain.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Where(clause="deleted = 0")
public class Job extends BaseEntity {
    private String jobTitle;
    private WorkSphere workSphere;
    private SubSphere subSphere;
    private int deliveryTime;
    private BigDecimal price;
    private String description;
    private String jobPicture;
    private UserProfileDetails userProfileDetails;
    private Set<UserProfileDetails> boughtByUser;

    public Job() {
    }

    @Column(name = "job_title", nullable = false)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @NotNull
    @ManyToOne
    public WorkSphere getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(WorkSphere workSphere) {
        this.workSphere = workSphere;
    }

    @NotNull
    @ManyToOne
    public SubSphere getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(SubSphere subSphere) {
        this.subSphere = subSphere;
    }

    @Column(name = "delivery_time", nullable = false)
    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false, columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "job_pictures")
    public String getJobPicture() {
        return jobPicture;
    }

    public void setJobPicture(String jobPicture) {
        this.jobPicture = jobPicture;
    }

    @NotNull
    @ManyToOne
    public UserProfileDetails getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(UserProfileDetails userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }

    @ManyToMany
    public Set<UserProfileDetails> getBoughtByUser() {
        return boughtByUser;
    }

    public void setBoughtByUser(Set<UserProfileDetails> boughtByUser) {
        this.boughtByUser = boughtByUser;
    }
}
