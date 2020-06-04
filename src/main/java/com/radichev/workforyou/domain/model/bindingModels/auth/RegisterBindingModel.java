package com.radichev.workforyou.domain.model.bindingModels.auth;

import java.util.Set;

public class RegisterBindingModel {
    private String username;
    private String password;
    private Set<RoleBindingModel> authorities;

    public RegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleBindingModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleBindingModel> authorities) {
        this.authorities = authorities;
    }
}
