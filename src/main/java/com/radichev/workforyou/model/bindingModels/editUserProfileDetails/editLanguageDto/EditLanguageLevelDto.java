package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editLanguageDto;

import javax.validation.constraints.NotNull;

public class EditLanguageLevelDto {
    private String languageLevel;

    public EditLanguageLevelDto() {
    }

    @NotNull
    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }
}
