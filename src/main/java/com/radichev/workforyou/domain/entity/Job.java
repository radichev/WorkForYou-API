package com.radichev.workforyou.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {
    private String jobTitle;
    private Set<WorkSphere> workSpheres;
    //TODO to add search tags
    //TODO to fix delivery times
    private int deliveryTime;
    private BigDecimal price;
    private String description;
    private Set<Picture> pictures;

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
    @ManyToMany
    public Set<WorkSphere> getWorkSpheres() {
        return workSpheres;
    }

    public void setWorkSpheres(Set<WorkSphere> workSpheres) {
        this.workSpheres = workSpheres;
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

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
