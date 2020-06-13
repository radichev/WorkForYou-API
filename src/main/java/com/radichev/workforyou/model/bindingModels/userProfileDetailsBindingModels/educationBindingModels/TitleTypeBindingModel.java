package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.educationBindingModels;

import javax.validation.constraints.NotNull;

public class TitleTypeBindingModel {
    private String titleType;

    public TitleTypeBindingModel() {
    }

    @NotNull
    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }
}
