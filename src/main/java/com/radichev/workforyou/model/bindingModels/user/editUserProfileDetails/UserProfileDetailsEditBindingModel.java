package com.radichev.workforyou.model.bindingModels.user.editUserProfileDetails;

import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserProfileDetailsEditBindingModel {
    private String id;
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private CountryDto country;
    private String email; //TODO check if needed

    public UserProfileDetailsEditBindingModel() {
    }

    @NotNull
    @NotBlank
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @NotBlank
    @Length(min = 2, max = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @NotBlank
    @Length(min = 2, max = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @NotBlank
    @Length(min = 15, max = 800)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(min = 4, max = 30)
    public String getPersonalWebsite() {
        return personalWebsite;
    }

    public void setPersonalWebsite(String personalWebsite) {
        this.personalWebsite = personalWebsite;
    }

    @NotNull
    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
