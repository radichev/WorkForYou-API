package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.InvalidEntityException;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.getUserProfileDetails.UserProfileDetailsViewModel;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.service.UserProfileDetailsService;
import com.radichev.workforyou.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserProfileDetailsServiceImpl(UserProfileDetailsRepository userProfileDetailsRepository, ModelMapper modelMapper, @Lazy UserService userService) {
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public UserProfileDetails createUserProfileDetails(String email) {
        UserProfileDetails userProfileDetails = new UserProfileDetails();
        userProfileDetails.setEmail(email);

        return this.userProfileDetailsRepository.saveAndFlush(userProfileDetails);
    }

    @Override
    @Transactional
    public void editUserProfileDetails(UserProfileDetailsEditBindingModel userProfileDetailsEditBindingModel, String id) {

        UserProfileDetails userProfileDetails = this.userService.findUserProfileDetailsById(id);

        this.modelMapper.map(userProfileDetailsEditBindingModel, userProfileDetails);

        this.userProfileDetailsRepository.save(userProfileDetails);
    }

    @Override
    public UserProfileDetailsViewModel getEditUserProfileDetails(String id) {
        return this.modelMapper.map(this.userService.findUserProfileDetailsById(id), UserProfileDetailsViewModel.class);
    }

    @Override
    public void uploadUserProfileImage(String userId, MultipartFile file) {

    }
}