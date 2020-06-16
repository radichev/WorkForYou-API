package com.radichev.workforyou.model.dtos.EducationDto;

import javax.validation.constraints.NotNull;

public class TitleTypeDto {
    private String titleType;

    public TitleTypeDto() {
    }

    @NotNull
    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }
}
