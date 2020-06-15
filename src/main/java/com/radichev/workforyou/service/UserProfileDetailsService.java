package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.auth.RegisterBindingModel;
import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.UserProfileDetailsBindingModel;

public interface UserProfileDetailsService {

    UserProfileDetails createUserProfileDetails(RegisterBindingModel registerBindingModel);

    void editUserProfileDetails(UserProfileDetailsBindingModel userProfileDetailsBindingModel, String id);

    UserProfileDetails getUserProfileDetails(String id);
}
