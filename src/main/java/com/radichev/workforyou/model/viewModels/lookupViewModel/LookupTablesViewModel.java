package com.radichev.workforyou.model.viewModels.lookupViewModel;

import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;
import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;

import java.util.List;

public class LookupTablesViewModel {
    private List<LanguageLevelDto> languageLevelDtos;
    private List<SkillLevelDto> skillLevelDtos;
    private List<TitleTypeDto> titleTypeDtos;
    private List<CountryDto> countryDtos;

    public LookupTablesViewModel() {
    }

    public List<LanguageLevelDto> getLanguageLevelDtos() {
        return languageLevelDtos;
    }

    public void setLanguageLevelDtos(List<LanguageLevelDto> languageLevelDtos) {
        this.languageLevelDtos = languageLevelDtos;
    }

    public List<SkillLevelDto> getSkillLevelDtos() {
        return skillLevelDtos;
    }

    public void setSkillLevelDtos(List<SkillLevelDto> skillLevelDtos) {
        this.skillLevelDtos = skillLevelDtos;
    }

    public List<TitleTypeDto> getTitleTypeDtos() {
        return titleTypeDtos;
    }

    public void setTitleTypeDtos(List<TitleTypeDto> titleTypeDtos) {
        this.titleTypeDtos = titleTypeDtos;
    }

    public List<CountryDto> getCountryDtos() {
        return countryDtos;
    }

    public void setCountryDtos(List<CountryDto> countryDtos) {
        this.countryDtos = countryDtos;
    }
}
