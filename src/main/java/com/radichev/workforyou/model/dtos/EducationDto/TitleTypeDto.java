package com.radichev.workforyou.model.dtos.EducationDto;

import javax.validation.constraints.NotNull;

public class TitleTypeDto {
    private String id;
    private String titleType;

    public TitleTypeDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }
}
