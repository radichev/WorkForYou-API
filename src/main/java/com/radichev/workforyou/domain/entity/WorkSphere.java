package com.radichev.workforyou.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "work_spheres")
public class WorkSphere extends BaseEntity {
    private String workSphere;
    private Set<SubSphere> subSpheres;
    private Set<Job> jobs;

    public WorkSphere() {
    }

    public WorkSphere(String workSphere, Set<SubSphere> subSpheres) {
        this.workSphere = workSphere;
        this.subSpheres = subSpheres;
    }

    @Column(name = "work_sphere", nullable = false)
    public String getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(String workSphere) {
        this.workSphere = workSphere;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workSphere")
//    @JoinColumn(name="work_sphere_id", referencedColumnName = "id")
    public Set<SubSphere> getSubSpheres() {
        return subSpheres;
    }

    public void setSubSpheres(Set<SubSphere> subSpheres) {
        this.subSpheres = subSpheres;
    }

    @OneToMany(mappedBy = "workSphere")
    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
