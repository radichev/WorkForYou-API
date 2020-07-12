package com.radichev.workforyou.model.dtos.PictureDto;

import javax.validation.constraints.NotNull;

public class PictureDto {
    private String id;
    private String pictureUrl;

    public PictureDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
