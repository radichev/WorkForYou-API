package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.EditUserProfileDetailsBindingModel;

public interface UserProfileDetailsService {

    UserProfileDetails createUserProfileDetails(String email);

    void editUserProfileDetails(EditUserProfileDetailsBindingModel editUserProfileDetailsBindingModel, String id);

    UserProfileDetails getUserProfileDetails(String id);
}
