package com.radichev.workforyou.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
    private String country;
    private Set<UserProfileDetails> userProfileDetails;

    public Country() {
    }

    public Country(String country) {
        this.country = country;
    }

    @Column(nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "country")
    public Set<UserProfileDetails> getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(Set<UserProfileDetails> userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }
}
