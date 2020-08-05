package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.model.bindingModels.auth.SignInBindingModel;
import com.radichev.workforyou.model.bindingModels.auth.SignUpBindingModel;
import com.radichev.workforyou.model.viewModels.auth.SignInViewModel;
import com.radichev.workforyou.model.viewModels.auth.SignUpViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    SignUpViewModel signUpUser(SignUpBindingModel signUpBindingModel);

    Optional<User> findByUsername(String username);

    SignInViewModel signInUser(SignInBindingModel signInBindingModel);
}
