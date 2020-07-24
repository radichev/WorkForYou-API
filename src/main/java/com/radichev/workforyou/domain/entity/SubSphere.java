package com.radichev.workforyou.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sub_spheres")
public class SubSphere extends BaseEntity {
    private String subSphere;
    private WorkSphere workSphere;
    private Set<Job> job;

    public SubSphere() {
    }

    public SubSphere(String subSphere) {
        this.subSphere = subSphere;
    }

    @Column(name = "display_name", nullable = false, unique = true)
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

    @OneToMany(mappedBy = "subSphere")
    public Set<Job> getJob() {
        return job;
    }

    public void setJob(Set<Job> job) {
        this.job = job;
    }
}
