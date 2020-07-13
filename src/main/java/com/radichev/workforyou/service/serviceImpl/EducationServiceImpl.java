package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.domain.entity.Education;
import com.radichev.workforyou.domain.entity.TitleType;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.educationBindingModel.EducationBindingModel;
import com.radichev.workforyou.repository.EducationRepository;
import com.radichev.workforyou.service.CountryService;
import com.radichev.workforyou.service.EducationService;
import com.radichev.workforyou.service.TitleTypeService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;
    private final CountryService countryService;
    private final TitleTypeService titleTypeService;

    public EducationServiceImpl(EducationRepository educationRepository, ModelMapper modelMapper, UserProfileDetailsService userProfileDetailsService, CountryService countryService, TitleTypeService titleTypeService) {
        this.educationRepository = educationRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
        this.countryService = countryService;
        this.titleTypeService = titleTypeService;
    }

    @Override
    public void addEducation(EducationBindingModel educationBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        Country country = this.countryService.findCountryById(educationBindingModel.getCountry().getId());

        TitleType titleType = this.titleTypeService.findTitleTypeById(educationBindingModel.getTitleType().getId());

        Education education = this.modelMapper.map(educationBindingModel, Education.class);

        education.setUserProfileDetails(userProfileDetails);
        education.setCountry(country);
        education.setTitleType(titleType);

        this.educationRepository.saveAndFlush(education);
    }
}
