package com.radichev.workforyou.model.dtos.PictureDto;

import javax.validation.constraints.NotNull;

public class PictureDto {
    private String pictureUrl;

    public PictureDto() {
    }

    @NotNull
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
