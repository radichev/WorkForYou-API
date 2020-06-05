package com.radichev.workforyou.domain.model.viewModels.auth;

import javax.validation.constraints.NotNull;

public class RegisterViewModel {
    @NotNull
    private String id;

    public RegisterViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
