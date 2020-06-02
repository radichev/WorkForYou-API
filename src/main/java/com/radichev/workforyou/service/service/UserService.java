package com.radichev.workforyou.service.service;

import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.service.model.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(UserServiceModel userServiceModel);
}
