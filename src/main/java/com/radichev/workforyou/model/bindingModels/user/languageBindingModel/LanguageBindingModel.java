package com.radichev.workforyou.model.bindingModels.user.languageBindingModel;

import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LanguageBindingModel {
    private String language;
    private LanguageLevelDto languageLevel;

    public LanguageBindingModel() {
    }

    @NotNull
    @NotBlank
    @Length(min = 3, max = 30)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @NotNull
    public LanguageLevelDto getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(LanguageLevelDto languageLevel) {
        this.languageLevel = languageLevel;
    }
}
