package com.radichev.workforyou.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "work_spheres")
public class WorkSphere extends BaseEntity {
    String workSphere;
    Set<SubSphere> subSpheres;

    public WorkSphere() {
    }

    @Column(name = "work_sphere", nullable = false)
    public String getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(String workSphere) {
        this.workSphere = workSphere;
    }

    @OneToMany(mappedBy = "workSphere")
    public Set<SubSphere> getSubSpheres() {
        return subSpheres;
    }

    public void setSubSpheres(Set<SubSphere> subSpheres) {
        this.subSpheres = subSpheres;
    }
}
