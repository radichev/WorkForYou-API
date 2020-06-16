package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.languageBindingModels;

import javax.validation.constraints.NotNull;

public class LanguageBindingModel {
    private String language;
    private LanguageLevelBindingModel languageLevel;

    public LanguageBindingModel() {
    }

    @NotNull
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @NotNull
    public LanguageLevelBindingModel getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(LanguageLevelBindingModel languageLevel) {
        this.languageLevel = languageLevel;
    }
}
