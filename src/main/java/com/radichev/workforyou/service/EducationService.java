package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.user.educationBindingModel.EducationBindingModel;

public interface EducationService {

    void addEducation(EducationBindingModel educationBindingModel, String userId);
}
