package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.user.editUserProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.getUserProfileDetails.UserProfileDetailsViewModel;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileDetailsService {

    UserProfileDetails createUserProfileDetails(String email);

    UserProfileDetailsViewModel editUserProfileDetails(UserProfileDetailsEditBindingModel userProfileDetailsEditBindingModel, String id);

    UserProfileDetails findUserProfileDetailsById(String userId);

    void uploadUserProfileImage(String userId, MultipartFile file);

    byte[] downloadUserProfileImage(String userId);
}
