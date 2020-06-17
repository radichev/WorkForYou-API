package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.InvalidEntityException;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.EditUserProfileDetailsBindingModel;
import com.radichev.workforyou.model.viewModels.editUserProfileDetails.EditUserProfileDetailsViewModel;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserProfileDetailsServiceImpl(UserProfileDetailsRepository userProfileDetailsRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserProfileDetails createUserProfileDetails(String email) {
        UserProfileDetails userProfileDetails = new UserProfileDetails();
        userProfileDetails.setEmail(email);

        return this.userProfileDetailsRepository.saveAndFlush(userProfileDetails);
    }

    @Override
    @Transactional
    public void editUserProfileDetails(EditUserProfileDetailsBindingModel editUserProfileDetailsBindingModel, String id) {

        UserProfileDetails userProfileDetails = this.userRepository.findUserProfileDetails(id)
                .orElseThrow(() ->
                        new InvalidEntityException(String.format("UserProfileDetails not found with %s id.", id))
                );

        this.modelMapper.map(editUserProfileDetailsBindingModel, userProfileDetails);

        this.userProfileDetailsRepository.save(userProfileDetails);
    }

    @Override
    public EditUserProfileDetailsViewModel getEditUserProfileDetails(String id) {
        return this.modelMapper.map(this.userRepository.findUserProfileDetails(id)
                .orElseThrow(() ->
                        new InvalidEntityException(String.format("UserProfileDetails not found with %s id.", id))
                ), EditUserProfileDetailsViewModel.class);
    }
}