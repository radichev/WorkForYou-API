package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editEducationDto;

import javax.validation.constraints.NotNull;

public class EditCountryDto {
    private String country;

    public EditCountryDto() {
    }

    @NotNull
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
