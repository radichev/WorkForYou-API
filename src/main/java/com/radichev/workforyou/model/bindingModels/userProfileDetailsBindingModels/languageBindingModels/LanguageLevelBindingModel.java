package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.languageBindingModels;

import javax.validation.constraints.NotNull;

public class LanguageLevelBindingModel {
    private String languageLevel;

    public LanguageLevelBindingModel() {
    }

    @NotNull
    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }
}
