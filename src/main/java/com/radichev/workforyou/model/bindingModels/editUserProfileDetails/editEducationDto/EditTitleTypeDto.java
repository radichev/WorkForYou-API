package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editEducationDto;

import javax.validation.constraints.NotNull;

public class EditTitleTypeDto {
    private String titleType;

    public EditTitleTypeDto() {
    }

    @NotNull
    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }
}
