package com.radichev.workforyou.model.viewModels.auth;

import javax.validation.constraints.NotNull;

public class SignUpViewModel {
    @NotNull
    private String id;

    public SignUpViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
