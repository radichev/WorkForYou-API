package com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.pictureBindingModels;

import javax.validation.constraints.NotNull;

public class PictureBindingModel {
    private String pictureUrl;

    public PictureBindingModel() {
    }

    @NotNull
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
