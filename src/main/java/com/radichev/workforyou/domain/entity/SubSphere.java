package com.radichev.workforyou.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "sub_spheres")
public class SubSphere extends BaseEntity {
    private String subSphere;
    private String subSpherePicture;
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

    @Column
    public String getSubSpherePicture() {
        return subSpherePicture;
    }

    public void setSubSpherePicture(String subSpherePicture) {
        this.subSpherePicture = subSpherePicture;
    }

    @ManyToOne
    public WorkSphere getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(WorkSphere workSphere) {
        this.workSphere = workSphere;
    }
}
