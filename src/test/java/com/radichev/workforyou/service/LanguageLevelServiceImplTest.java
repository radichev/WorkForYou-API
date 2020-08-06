package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.LanguageLevel;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;
import com.radichev.workforyou.repository.LanguageLevelRepository;
import com.radichev.workforyou.service.serviceImpl.LanguageLevelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LanguageLevelServiceImplTest {

    private LanguageLevelService languageLevelService;

    @Mock
    private LanguageLevelRepository languageLevelRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private LanguageLevel languageLevel;

    @BeforeEach
    public void setUp() {
        this.languageLevelService = new LanguageLevelServiceImpl(languageLevelRepository, modelMapper);

        languageLevel = new LanguageLevel("Fluent");
    }

    @Test
    public void testFindAllLanguageLevelsShouldReturnCorrectResult() {
        when(this.languageLevelRepository.findAll())
                .thenReturn(List.of(languageLevel));

        List<LanguageLevelDto> testLanguageLevelCollection = this.languageLevelService.findAllLanguageLevels();

        Assertions.assertEquals(1, testLanguageLevelCollection.size());
        LanguageLevelDto testLanguageLevel = testLanguageLevelCollection.get(0);

        Assertions.assertEquals(languageLevel.getLanguageLevel(), testLanguageLevel.getLanguageLevel());
    }

    @Test
    public void testFindLanguageLevelByIdShouldReturnCorrectResult() {
        when(this.languageLevelRepository.findById("testId"))
                .thenReturn(Optional.ofNullable(languageLevel));

        LanguageLevel testLanguageLevel = this.languageLevelService.findLanguageLevelById("testId");

        Assertions.assertEquals(languageLevel.getLanguageLevel(), testLanguageLevel.getLanguageLevel());
    }

    @Test
    public void testFindLanguageLevelByIdShouldThrowEntityNotFoundExceptionWithInvalid() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.languageLevelService.findLanguageLevelById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "LanguageLevel not found with testId id.");
    }
}
