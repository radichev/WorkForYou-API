package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.EditUserProfileDetailsBindingModel;
import com.radichev.workforyou.model.viewModels.editUserProfileDetails.EditUserProfileDetailsViewModel;

public interface UserProfileDetailsService {

    UserProfileDetails createUserProfileDetails(String email);

    void editUserProfileDetails(EditUserProfileDetailsBindingModel editUserProfileDetailsBindingModel, String id);

    EditUserProfileDetailsViewModel getUserProfileDetails(String id);
}
