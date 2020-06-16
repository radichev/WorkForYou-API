package com.radichev.workforyou.model.dtos.LanguageDto;

import javax.validation.constraints.NotNull;

public class LanguageDto {
    private String language;
    private LanguageLevelDto languageLevel;

    public LanguageDto() {
    }

    @NotNull
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
