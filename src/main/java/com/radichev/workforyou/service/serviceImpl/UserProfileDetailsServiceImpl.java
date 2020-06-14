package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.auth.RegisterBindingModel;
import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.UserProfileDetailsBindingModel;
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
    public UserProfileDetails createUserProfileDetails(RegisterBindingModel registerBindingModel) {
        UserProfileDetails userProfileDetails = this.modelMapper.map(registerBindingModel, UserProfileDetails.class);
        
        return this.userProfileDetailsRepository.saveAndFlush(userProfileDetails);
    }

    @Override
    public void editUserProfileDetails(UserProfileDetailsBindingModel userProfileDetailsBindingModel) {
        UserProfileDetails userProfileDetails = this.modelMapper.map(userProfileDetailsBindingModel, UserProfileDetails.class);

        System.out.println();
    }
}
