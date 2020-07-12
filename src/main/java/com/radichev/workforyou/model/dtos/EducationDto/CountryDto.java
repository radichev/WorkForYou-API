package com.radichev.workforyou.model.dtos.EducationDto;

import javax.validation.constraints.NotNull;

public class CountryDto {
    private String id;
    private String country;

    public CountryDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
