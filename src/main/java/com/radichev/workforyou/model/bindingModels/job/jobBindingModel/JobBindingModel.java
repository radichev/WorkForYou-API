package com.radichev.workforyou.model.bindingModels.job.jobBindingModel;

import com.radichev.workforyou.model.bindingModels.WorkSphereBindingModel;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class JobBindingModel {
    private String jobTitle;
    private WorkSphereBindingModel workSphere;
    private int deliveryTime;
    private BigDecimal price;
    private String description;

    public JobBindingModel() {
    }

    @NotNull
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @NotNull
    public WorkSphereBindingModel getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(WorkSphereBindingModel workSphere) {
        this.workSphere = workSphere;
    }

    @NotNull
    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
