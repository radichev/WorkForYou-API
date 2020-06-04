package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private final UserProfileDetailsRepository userProfileDetailsRepository;

    public UserProfileDetailsServiceImpl(UserProfileDetailsRepository userProfileDetailsRepository) {
        this.userProfileDetailsRepository = userProfileDetailsRepository;
    }


}
