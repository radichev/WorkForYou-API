package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.user.userProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.userProfileDetails.UserProfileDetailsAdminModel;
import com.radichev.workforyou.model.viewModels.userProfileDetails.UserProfileDetailsViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserProfileDetailsService {

    UserProfileDetails createUserProfileDetails(String email);

    UserProfileDetailsViewModel editUserProfileDetails(UserProfileDetailsEditBindingModel userProfileDetailsEditBindingModel, String id);

    UserProfileDetails findUserProfileDetailsById(String userId);

    void uploadUserProfileImage(String userId, MultipartFile file);

    List<UserProfileDetailsAdminModel> findAllUsers();
}
