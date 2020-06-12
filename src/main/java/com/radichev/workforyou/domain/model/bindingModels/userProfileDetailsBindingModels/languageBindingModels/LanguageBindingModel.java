package com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.languageBindingModels;

import javax.validation.constraints.NotNull;

public class LanguageBindingModel {
    private String language;
    private LanguageLevelBindingModel languageLevelBindingModel;

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
    public LanguageLevelBindingModel getLanguageLevelBindingModel() {
        return languageLevelBindingModel;
    }

    public void setLanguageLevelBindingModel(LanguageLevelBindingModel languageLevelBindingModel) {
        this.languageLevelBindingModel = languageLevelBindingModel;
    }
}
