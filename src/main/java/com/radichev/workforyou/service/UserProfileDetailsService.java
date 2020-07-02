package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.getUserProfileDetails.UserProfileDetailsViewModel;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileDetailsService {

    UserProfileDetails createUserProfileDetails(String email);

    void editUserProfileDetails(UserProfileDetailsEditBindingModel userProfileDetailsEditBindingModel, String id);

    UserProfileDetailsViewModel getEditUserProfileDetails(String id);

    void uploadUserProfileImage(String userId, MultipartFile file);

    byte[] downloadUserProfileImage(String userId);
}
