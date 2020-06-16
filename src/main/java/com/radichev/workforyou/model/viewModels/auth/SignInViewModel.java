package com.radichev.workforyou.model.viewModels.auth;

import javax.validation.constraints.NotNull;

public class SignInViewModel {
    @NotNull
    private String jwt;


    public SignInViewModel() {
    }

    public SignInViewModel(@NotNull String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
