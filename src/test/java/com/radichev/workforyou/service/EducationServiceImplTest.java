package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.domain.entity.Education;
import com.radichev.workforyou.domain.entity.TitleType;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.educationBindingModel.EducationBindingModel;
import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.model.dtos.EducationDto.EducationDto;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;
import com.radichev.workforyou.repository.EducationRepository;
import com.radichev.workforyou.service.serviceImpl.EducationServiceImpl;
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
public class EducationServiceImplTest {

    private EducationService educationService;

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private UserProfileDetailsService userProfileDetailsService;

    @Mock
    private CountryService countryService;

    @Mock
    private TitleTypeService titleTypeService;

    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        this.educationService = new EducationServiceImpl(educationRepository,
                                                         modelMapper,
                                                         userProfileDetailsService,
                                                         countryService,
                                                         titleTypeService);
    }

    @Test
    public void testAddEducationShouldReturnCorrectResult() {
        Country country = new Country("Bulgaria");
        TitleType titleType = new TitleType("Ph.D");

        UserProfileDetails userProfileDetails = new UserProfileDetails();
        userProfileDetails.setFirstName("Ivan");
        userProfileDetails.setLastName("Petrov");
        userProfileDetails.setEmail("asd@abv.bg");

        Education education = new Education();
        education.setUniversityName("Softuni");
        education.setEducationSubject("Spring");
        education.setGraduationYear(2012);
        education.setTitleType(titleType);
        education.setCountry(country);

        when(this.userProfileDetailsService.findUserProfileDetailsById("testId"))
                .thenReturn(userProfileDetails);

        when(this.educationRepository.saveAndFlush(Mockito.any(Education.class)))
                .thenReturn(education);

        EducationDto testEducation = this.educationService
                .addEducation(this.modelMapper.map(education, EducationBindingModel.class), "testId");

        Assertions.assertEquals(education.getEducationSubject(), testEducation.getEducationSubject());
        Assertions.assertEquals(education.getGraduationYear(), testEducation.getGraduationYear());
        Assertions.assertEquals(education.getUniversityName(), testEducation.getUniversityName());
        Assertions.assertEquals(education.getCountry().getCountry(), testEducation.getCountry().getCountry());
        Assertions.assertEquals(education.getTitleType().getTitleType(), testEducation.getTitleType().getTitleType());
    }

    @Test
    public void testFindEducationByIdShouldReturnCorrectResult() {
        Country country = new Country("Bulgaria");
        TitleType titleType = new TitleType("Ph.D");

        Education education = new Education();
        education.setUniversityName("Softuni");
        education.setEducationSubject("Spring");
        education.setGraduationYear(2012);
        education.setTitleType(titleType);
        education.setCountry(country);

        when(this.educationRepository.findById("testId"))
                .thenReturn(Optional.of(education));

        Education testEducation = this.educationService.findEducationById("testId");

        Assertions.assertEquals(education.getEducationSubject(), testEducation.getEducationSubject());
        Assertions.assertEquals(education.getGraduationYear(), testEducation.getGraduationYear());
        Assertions.assertEquals(education.getUniversityName(), testEducation.getUniversityName());
        Assertions.assertEquals(education.getCountry().getCountry(), testEducation.getCountry().getCountry());
        Assertions.assertEquals(education.getTitleType().getTitleType(), testEducation.getTitleType().getTitleType());
    }

    @Test
    public void testFindEducationByIdShouldThrowEntityNotFoundExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.educationService.findEducationById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "Education not found with testId id.");
    }

    @Test
    public void testEditEducationByIdShouldReturnCorrectResult() {
        Country country = new Country("Bulgaria");
        TitleType titleType = new TitleType("BC");

        CountryDto countryDto = new CountryDto();
        countryDto.setCountry("France");
        TitleTypeDto titleTypeDto = new TitleTypeDto();
        titleTypeDto.setTitleType("Ph.D");

        Education education = new Education();
        education.setUniversityName("Softuni");
        education.setEducationSubject("Spring");
        education.setGraduationYear(2012);
        education.setTitleType(titleType);
        education.setCountry(country);



        when(this.educationRepository.findById("testId"))
                .thenReturn(Optional.of(education));

        when(this.educationRepository.save(Mockito.any(Education.class)))
                .thenReturn(education);

        EducationBindingModel educationBindingModel = new EducationBindingModel();
        educationBindingModel.setUniversityName("FMI");
        educationBindingModel.setEducationSubject("Database");
        educationBindingModel.setGraduationYear(2021);
        educationBindingModel.setTitleType(titleTypeDto);
        educationBindingModel.setCountry(countryDto);

        when(this.countryService.findCountryById(educationBindingModel.getCountry().getId()))
                .thenReturn(this.modelMapper.map(countryDto, Country.class));

        when(this.titleTypeService.findTitleTypeById(educationBindingModel.getTitleType().getId()))
                .thenReturn(this.modelMapper.map(titleTypeDto, TitleType.class));

        EducationDto testEducation = this.educationService.editEducationById("testId", educationBindingModel);

        Assertions.assertEquals(educationBindingModel.getEducationSubject(), testEducation.getEducationSubject());
        Assertions.assertEquals(educationBindingModel.getGraduationYear(), testEducation.getGraduationYear());
        Assertions.assertEquals(educationBindingModel.getUniversityName(), testEducation.getUniversityName());
        Assertions.assertEquals(educationBindingModel.getCountry().getCountry(), testEducation.getCountry().getCountry());
        Assertions.assertEquals(educationBindingModel.getTitleType().getTitleType(), testEducation.getTitleType().getTitleType());
    }
}
