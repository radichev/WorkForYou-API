package com.radichev.workforyou.model.dtos.LanguageDto;

import javax.validation.constraints.NotNull;

public class LanguageLevelDto {
    private String languageLevel;

    public LanguageLevelDto() {
    }

    @NotNull
    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }
}
