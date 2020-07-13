package com.radichev.workforyou.model.bindingModels.languageBindingModel;

import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;

public class LanguageBindingModel {
    private String language;
    private LanguageLevelDto languageLevel;

    public LanguageBindingModel() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LanguageLevelDto getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(LanguageLevelDto languageLevel) {
        this.languageLevel = languageLevel;
    }
}
