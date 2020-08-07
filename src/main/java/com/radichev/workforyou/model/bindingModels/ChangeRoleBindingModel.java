package com.radichev.workforyou.model.bindingModels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangeRoleBindingModel {
    private String username;
    private String authority;

    public ChangeRoleBindingModel() {
    }

    @NotNull
    @NotBlank
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @NotBlank
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
