package com.radichev.workforyou.domain.entity;

import com.radichev.workforyou.domain.entity.auth.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users_profile_details")
public class UserProfileDetails extends BaseEntity {
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private Picture profilePicture;
    private String country;
    private String email;
    private Set<Language> language;
    private Set<WorkSphere> workSpheres;
    private Set<Skill> skills;
    private Set<Education> educations;
    private Set<Certificate> certificates;
    private User user;

    public UserProfileDetails() {
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
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

    @OneToOne
    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
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

    @ManyToMany
    public Set<Language> getLanguage() {
        return language;
    }

    public void setLanguage(Set<Language> language) {
        this.language = language;
    }

    @ManyToMany
    public Set<WorkSphere> getWorkSpheres() {
        return workSpheres;
    }

    public void setWorkSpheres(Set<WorkSphere> workSpheres) {
        this.workSpheres = workSpheres;
    }

    @ManyToMany
    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @ManyToMany
    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    @OneToMany(mappedBy = "userProfileDetails")
    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
