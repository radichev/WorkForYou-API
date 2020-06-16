package com.radichev.workforyou.model.dtos.EducationDto;

import javax.validation.constraints.NotNull;

public class CountryDto {
    private String country;

    public CountryDto() {
    }

    @NotNull
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
