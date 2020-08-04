package com.radichev.workforyou.model.bindingModels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubSphereBindingModel {
    private String id;

    public SubSphereBindingModel() {
    }

    @NotNull
    @NotBlank
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
