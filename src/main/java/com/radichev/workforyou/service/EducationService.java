package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Education;
import com.radichev.workforyou.model.bindingModels.user.educationBindingModel.EducationBindingModel;
import com.radichev.workforyou.model.dtos.EducationDto.EducationDto;

public interface EducationService {

    EducationDto addEducation(EducationBindingModel educationBindingModel, String userId);

    Education findEducationById(String educationId);

    void deleteCertificateById(String educationId);
}
