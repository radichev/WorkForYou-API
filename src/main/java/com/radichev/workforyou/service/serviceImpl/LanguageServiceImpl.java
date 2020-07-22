package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Language;
import com.radichev.workforyou.domain.entity.LanguageLevel;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.user.languageBindingModel.LanguageBindingModel;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageDto;
import com.radichev.workforyou.repository.LanguageRepository;
import com.radichev.workforyou.service.LanguageLevelService;
import com.radichev.workforyou.service.LanguageService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;
    private final LanguageLevelService languageLevelService;

    public LanguageServiceImpl(LanguageRepository languageRepository, ModelMapper modelMapper, UserProfileDetailsService userProfileDetailsService, LanguageLevelService languageLevelService) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
        this.languageLevelService = languageLevelService;
    }

    @Override
    public LanguageDto addLanguage(LanguageBindingModel languageBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        LanguageLevel languageLevel = this.languageLevelService.findLanguageLevelById(languageBindingModel.getLanguageLevel().getId());

        Language language = this.languageRepository.findByLanguage(languageBindingModel.getLanguage());

        if (language == null) {
            language = this.modelMapper.map(languageBindingModel, Language.class);
        }

        if (language.getUserProfileDetails() != null) {
            language.getUserProfileDetails().add(userProfileDetails);
        } else {
            language.setUserProfileDetails(Set.of(userProfileDetails));
        }

        language.setLanguageLevel(languageLevel);

        return this.modelMapper.map(this.languageRepository.saveAndFlush(language), LanguageDto.class);
    }
}
