package com.radichev.workforyou.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "sub_spheres")
public class SubSphere extends BaseEntity {
    private String subSphere;
    private WorkSphere workSphere;

    public SubSphere() {
    }

    public SubSphere(String subSphere) {
        this.subSphere = subSphere;
    }

    @Column(nullable = false, unique = true)
    public String getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(String subSphere) {
        this.subSphere = subSphere;
    }

    @ManyToOne
    public WorkSphere getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(WorkSphere workSphere) {
        this.workSphere = workSphere;
    }
}
