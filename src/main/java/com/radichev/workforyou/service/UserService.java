package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.model.bindingModels.auth.LoginBindingModel;
import com.radichev.workforyou.model.bindingModels.auth.RegisterBindingModel;
import com.radichev.workforyou.model.viewModels.auth.JwtViewModel;
import com.radichev.workforyou.model.viewModels.auth.RegisterViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    RegisterViewModel register(RegisterBindingModel registerBindingModel);

    Optional<User> findByUsername(String username);

    JwtViewModel loginUser(LoginBindingModel loginBindingModel);
}
