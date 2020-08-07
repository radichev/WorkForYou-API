package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.userProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.model.viewModels.userProfileDetails.UserProfileDetailsViewModel;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.service.serviceImpl.UserProfileDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserProfileDetailsServiceImplTest {

    private UserProfileDetailsService userProfileDetailsService;

    @Mock
    private UserProfileDetailsRepository userProfileDetailsRepository;

    @Mock
    private FileStoreService fileStoreService;

    @Mock
    private CountryService countryService;

    private ModelMapper modelMapper = new ModelMapper();
    private UserProfileDetails userProfileDetails;
    private UserProfileDetailsEditBindingModel userProfileDetailsBindingModel;
    private CountryDto testCountry;

    private final String VALID_FILE_NAME = "validFileName";
    private final byte[] VALID_DATA = new byte[]{11, 11};

    @BeforeEach
    public void setUp() {
        this.userProfileDetailsService = new UserProfileDetailsServiceImpl(userProfileDetailsRepository,
                                                                           modelMapper,
                                                                           fileStoreService,
                                                                           countryService);

        userProfileDetails = new UserProfileDetails();
        userProfileDetails.setFirstName("Ivan");
        userProfileDetails.setLastName("Petrov");
        userProfileDetails.setEmail("asd@abv.bg");
        userProfileDetails.setHasCompletedAccountSetup(true);
        userProfileDetails.setCountry(new Country("Bulgaria"));
        userProfileDetails.setUser(new User());

        userProfileDetailsBindingModel = new UserProfileDetailsEditBindingModel();
        userProfileDetailsBindingModel.setFirstName("Pesho");
        userProfileDetailsBindingModel.setLastName("Ivanov");
        userProfileDetailsBindingModel.setEmail("test@abv.bg");

        testCountry = new CountryDto();
        testCountry.setCountry("Spain");
        userProfileDetailsBindingModel.setCountry(testCountry);

    }

    @Test
    public void testCreateUserProfileDetailsShouldReturnCorrectResult() {
        when(this.userProfileDetailsRepository.saveAndFlush(Mockito.any(UserProfileDetails.class)))
                .thenReturn(userProfileDetails);

        UserProfileDetails testUserProfileDetails = this.userProfileDetailsService.createUserProfileDetails("testEmail@abv.bg");

        Assertions.assertEquals(userProfileDetails.getEmail(), testUserProfileDetails.getEmail());
    }

    @Test
    public void testEditUserProfileDetailsShouldReturnCorrectResult() {
        when(this.userProfileDetailsRepository.findByUserId("testId"))
                .thenReturn(Optional.ofNullable(userProfileDetails));

        when(this.countryService.findCountryById(userProfileDetails.getCountry().getId()))
                .thenReturn(this.modelMapper.map(testCountry, Country.class));

        when(this.userProfileDetailsRepository.save(userProfileDetails))
                .thenReturn(userProfileDetails);

        UserProfileDetailsViewModel testUserProfileDetails = this.userProfileDetailsService
                .editUserProfileDetails(userProfileDetailsBindingModel, "testId");

        Assertions.assertEquals(true, testUserProfileDetails.getHasCompletedAccountSetup());
        Assertions.assertEquals(userProfileDetails.getFirstName(), testUserProfileDetails.getFirstName());
        Assertions.assertEquals(userProfileDetails.getLastName(), testUserProfileDetails.getLastName());
        Assertions.assertEquals(userProfileDetails.getEmail(), testUserProfileDetails.getEmail());
        Assertions.assertEquals(userProfileDetails.getCountry().getCountry(), testUserProfileDetails.getCountry().getCountry());
    }

    @Test
    public void testFindUserProfileDetailsByIdShouldReturnCorrectResult() {
        when(this.userProfileDetailsRepository.findByUserId("testId"))
                .thenReturn(Optional.ofNullable(userProfileDetails));

        UserProfileDetails testUserProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById("testId");

        Assertions.assertEquals(userProfileDetails.getFirstName(), testUserProfileDetails.getFirstName());
        Assertions.assertEquals(userProfileDetails.getLastName(), testUserProfileDetails.getLastName());
        Assertions.assertEquals(userProfileDetails.getEmail(), testUserProfileDetails.getEmail());
    }
    @Test
    public void testFindUserProfileDetailsByIdShouldThrowEntityNotFoundExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.userProfileDetailsService.findUserProfileDetailsById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "UserProfileDetails not found with testId user id.");
    }

    @Test
    public void testUploadUserProfileImageShouldThrowIllegalStateExceptionWithWrongFileType() {
        MultipartFile multipartFile = new MockMultipartFile("picture", VALID_FILE_NAME, "invalidType", VALID_DATA);

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            this.userProfileDetailsService.uploadUserProfileImage("testId", multipartFile);
        });

        Assertions.assertEquals(exception.getMessage(), "File must be an image");
    }

    @Test
    public void testUploadUserProfileImageShouldThrowIllegalStateExceptionWithEmptyFile() {
        byte[] emptyFile = null;
        MultipartFile multipartFile = new MockMultipartFile("picture", VALID_FILE_NAME, "invalidType", emptyFile);

        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            this.userProfileDetailsService.uploadUserProfileImage("testId", multipartFile);
        });

        Assertions.assertEquals(exception.getMessage(), "Cannot upload empty file");
    }
}
