package com.radichev.workforyou.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@DynamicUpdate
@Table(name = "users_profile_details")
public class UserProfileDetails extends BaseEntity {
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private String profilePicture;
    private String country;
    private String email;
    private Set<Language> languages;
//    private Set<WorkSphere> workSpheres;
    private Set<Skill> skills;
    private Set<Education> educations;
    private Set<Certificate> certificates;
    private Set<Job> jobs;

    public UserProfileDetails() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "personal_website")
    public String getPersonalWebsite() {
        return personalWebsite;
    }

    public void setPersonalWebsite(String personalWebsite) {
        this.personalWebsite = personalWebsite;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }


//    @ManyToMany(cascade = CascadeType.ALL)
//    public Set<WorkSphere> getWorkSpheres() {
//        return workSpheres;
//    }
//
//    public void setWorkSpheres(Set<WorkSphere> workSpheres) {
//        this.workSpheres = workSpheres;
//    }

    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_details_id", referencedColumnName = "id")
    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    @OneToMany
    @JoinColumn(name = "user_profile_details_id", referencedColumnName = "id")
    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
