package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.domain.entity.Education;
import com.radichev.workforyou.domain.entity.TitleType;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.educationBindingModel.EducationBindingModel;
import com.radichev.workforyou.model.dtos.EducationDto.EducationDto;
import com.radichev.workforyou.repository.EducationRepository;
import com.radichev.workforyou.service.CountryService;
import com.radichev.workforyou.service.EducationService;
import com.radichev.workforyou.service.TitleTypeService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;
    private final CountryService countryService;
    private final TitleTypeService titleTypeService;

    public EducationServiceImpl(EducationRepository educationRepository,
                                ModelMapper modelMapper,
                                UserProfileDetailsService userProfileDetailsService,
                                CountryService countryService,
                                TitleTypeService titleTypeService) {
        this.educationRepository = educationRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
        this.countryService = countryService;
        this.titleTypeService = titleTypeService;
    }

    @Override
    public EducationDto addEducation(EducationBindingModel educationBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        Country country = this.countryService.findCountryById(educationBindingModel.getCountry().getId());

        TitleType titleType = this.titleTypeService.findTitleTypeById(educationBindingModel.getTitleType().getId());

        Education education = this.modelMapper.map(educationBindingModel, Education.class);

        education.setUserProfileDetails(userProfileDetails);
        education.setCountry(country);
        education.setTitleType(titleType);

        return this.modelMapper.map(this.educationRepository.saveAndFlush(education), EducationDto.class);
    }

    @Override
    public Education findEducationById(String educationId) {
        return this.educationRepository.findById(educationId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Education not found with %s id.", educationId)));
    }

    @Override
    public void deleteEducationById(String educationId) {
        Education education = this.findEducationById(educationId);
        education.setDeleted(true);
        education.setDeletedOn(LocalDate.now());

        this.educationRepository.save(education);
    }

    @Override
    public EducationDto editEducationById(String educationId, EducationBindingModel educationBindingModel) {
        Education education = this.findEducationById(educationId);

        education.setUniversityName(educationBindingModel.getUniversityName());
        education.setEducationSubject(educationBindingModel.getEducationSubject());
        education.setGraduationYear(educationBindingModel.getGraduationYear());

        Country country = this.countryService.findCountryById(educationBindingModel.getCountry().getId());
        TitleType titleType = this.titleTypeService.findTitleTypeById(educationBindingModel.getTitleType().getId());

        education.setCountry(country);
        education.setTitleType(titleType);

        return this.modelMapper.map(this.educationRepository.save(education), EducationDto.class);
    }
}
