package com.radichev.workforyou.service;

import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;

import java.util.List;

public interface CountryService {
    void initCountries();

    List<CountryDto> findAllCountries();
}
