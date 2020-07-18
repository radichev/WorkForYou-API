package com.radichev.workforyou.model.viewModels.lookupViewModel;

import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;
import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;

import java.util.List;

public class UserLookupTablesViewModel {
    private List<LanguageLevelDto> languageLevels;
    private List<SkillLevelDto> skillLevels;
    private List<TitleTypeDto> titleTypes;
    private List<CountryDto> countries;

    public UserLookupTablesViewModel() {
    }

    public List<LanguageLevelDto> getLanguageLevels() {
        return languageLevels;
    }

    public void setLanguageLevels(List<LanguageLevelDto> languageLevels) {
        this.languageLevels = languageLevels;
    }

    public List<SkillLevelDto> getSkillLevels() {
        return skillLevels;
    }

    public void setSkillLevels(List<SkillLevelDto> skillLevels) {
        this.skillLevels = skillLevels;
    }

    public List<TitleTypeDto> getTitleTypes() {
        return titleTypes;
    }

    public void setTitleTypes(List<TitleTypeDto> titleTypes) {
        this.titleTypes = titleTypes;
    }

    public List<CountryDto> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }
}
