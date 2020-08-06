package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Language;
import com.radichev.workforyou.domain.entity.LanguageLevel;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.languageBindingModel.LanguageBindingModel;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageDto;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;
import com.radichev.workforyou.repository.LanguageRepository;
import com.radichev.workforyou.service.serviceImpl.LanguageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LanguageServiceImplTest {

    private LanguageService languageService;

    @Mock
    LanguageRepository languageRepository;

    @Mock
    UserProfileDetailsService userProfileDetailsService;

    @Mock
    LanguageLevelService languageLevelService;

    private ModelMapper modelMapper = new ModelMapper();
    private Language language;
    private UserProfileDetails userProfileDetails;
    private LanguageLevel languageLevel;
    private LanguageLevel testLanguageLevel;
    private LanguageBindingModel languageBindingModel;

    @BeforeEach
    public void setUp() {
        languageService = new LanguageServiceImpl(languageRepository,
                                                  modelMapper,
                                                  userProfileDetailsService,
                                                  languageLevelService);

        language = new Language();
        language.setLanguage("English");

        languageLevel = new LanguageLevel();
        languageLevel.setLanguageLevel("Fluent");
        language.setLanguageLevel(languageLevel);

        languageBindingModel = new LanguageBindingModel();
        languageBindingModel.setLanguage("Spanish");
        testLanguageLevel = new LanguageLevel();
        testLanguageLevel.setLanguageLevel("Basic");
        languageBindingModel.setLanguageLevel(this.modelMapper.map(testLanguageLevel, LanguageLevelDto.class));

        userProfileDetails = new UserProfileDetails();
        userProfileDetails.setFirstName("Ivan");
        userProfileDetails.setLastName("Petrov");
        userProfileDetails.setEmail("asd@abv.bg");
    }

    @Test
    public void testAddLanguageShouldReturnCorrectResult() {
        when(this.userProfileDetailsService.findUserProfileDetailsById("testId"))
                .thenReturn(userProfileDetails);

        when(this.languageRepository.saveAndFlush(Mockito.any(Language.class)))
                .thenReturn(language);

        when(this.languageLevelService.findLanguageLevelById(language.getLanguageLevel().getId()))
                .thenReturn(testLanguageLevel);

        LanguageBindingModel languageBindingModel = this.modelMapper.map(language, LanguageBindingModel.class);

        LanguageDto testLanguage = this.languageService.addLanguage(languageBindingModel, "testId");

        Assertions.assertEquals(language.getLanguage(), testLanguage.getLanguage());
        Assertions.assertEquals(language.getLanguageLevel().getLanguageLevel(), testLanguage.getLanguageLevel().getLanguageLevel());
    }

    @Test
    public void testFindLanguageByIdShouldReturnCorrectResult() {
        when(this.languageRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(language));

        Language testLanguage = this.languageService.findLanguageById("testId");

        Assertions.assertEquals(language.getLanguage(), testLanguage.getLanguage());
        Assertions.assertEquals(language.getLanguageLevel().getLanguageLevel(), testLanguage.getLanguageLevel().getLanguageLevel());
    }

    @Test
    public void testFindLanguageByIdShouldThrowEntityNotFoundExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.languageService.findLanguageById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "Language not found with testId id.");
    }

    @Test
    public void testEditLanguageShouldReturnCorrectResult() {
        when(this.languageRepository.save(Mockito.any(Language.class)))
                .thenReturn(this.modelMapper.map(languageBindingModel, Language.class));

        when(this.languageRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(language));

        when(this.languageLevelService.findLanguageLevelById(language.getLanguageLevel().getId()))
                .thenReturn(languageLevel);

        LanguageDto testLanguage = this.languageService.editLanguageById("testId", languageBindingModel);

        Assertions.assertEquals(languageBindingModel.getLanguage(), testLanguage.getLanguage());
        Assertions.assertEquals(languageBindingModel.getLanguageLevel().getLanguageLevel(), testLanguage.getLanguageLevel().getLanguageLevel());
    }
}
