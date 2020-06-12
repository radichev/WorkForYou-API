package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.UserProfileDetailsBindingModel;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final ModelMapper modelMapper;

    public UserProfileDetailsServiceImpl(UserProfileDetailsRepository userProfileDetailsRepository, ModelMapper modelMapper) {
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void editUserProfileDetails(UserProfileDetailsBindingModel userProfileDetailsBindingModel) {

    }
}
