package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editPictureDto;

import javax.validation.constraints.NotNull;

public class EditPictureDto {
    private String pictureUrl;

    public EditPictureDto() {
    }

    @NotNull
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
