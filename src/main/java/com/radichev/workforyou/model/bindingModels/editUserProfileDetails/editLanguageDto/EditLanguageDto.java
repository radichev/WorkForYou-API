package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editLanguageDto;

import javax.validation.constraints.NotNull;

public class EditLanguageDto {
    private String language;
    private EditLanguageLevelDto languageLevel;

    public EditLanguageDto() {
    }

    @NotNull
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @NotNull
    public EditLanguageLevelDto getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(EditLanguageLevelDto languageLevel) {
        this.languageLevel = languageLevel;
    }
}
