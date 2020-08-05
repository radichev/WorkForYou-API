package com.radichev.workforyou.domain.entity;

import com.radichev.workforyou.domain.entity.auth.User;
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
    private String email;
    private Country country;
    private Boolean hasCompletedAccountSetup;
    private Set<Language> languages;
    private Set<Skill> skills;
    private Set<Education> educations;
    private Set<Certificate> certificates;
    private Set<Job> jobs;
    private Set<Job> boughtJobs;
    private User user;

    public UserProfileDetails() {
        this.hasCompletedAccountSetup = false;
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

    @Column(name = "profile_picture", columnDefinition = "text")
    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfileDetails")
    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfileDetails")
    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfileDetails")
    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfileDetails")
    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    @OneToMany(mappedBy = "userProfileDetails")
    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @ManyToMany(mappedBy = "boughtByUser")
    public Set<Job> getBoughtJobs() {
        return boughtJobs;
    }

    public void setBoughtJobs(Set<Job> boughtJobs) {
        this.boughtJobs = boughtJobs;
    }

    @OneToOne(mappedBy = "userProfileDetails")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "has_completed_account_setup", nullable = false)
    public Boolean getHasCompletedAccountSetup() {
        return hasCompletedAccountSetup;
    }

    public void setHasCompletedAccountSetup(Boolean hasCompletedAccountSetup) {
        this.hasCompletedAccountSetup = hasCompletedAccountSetup;
    }
}
