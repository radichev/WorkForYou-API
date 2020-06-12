package com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.educationBindingModels;

import javax.validation.constraints.NotNull;

public class CountryBindingModel {
    private String country;

    public CountryBindingModel() {
    }

    @NotNull
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
