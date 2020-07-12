package com.radichev.workforyou.model.dtos.LanguageDto;

import javax.validation.constraints.NotNull;

public class LanguageLevelDto {
    private String id;
    private String languageLevel;

    public LanguageLevelDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }
}
