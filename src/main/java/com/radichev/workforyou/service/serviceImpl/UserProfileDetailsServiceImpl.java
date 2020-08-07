package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.bucket.BucketName;
import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.domain.entity.auth.Role;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.userProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.userProfileDetails.UserProfileDetailsAdminModel;
import com.radichev.workforyou.model.viewModels.userProfileDetails.UserProfileDetailsViewModel;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private static final String AWS_S3_DEFAULT_URL = "https://workforyou-images.s3.amazonaws.com/";
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final ModelMapper modelMapper;
    private final FileStoreService fileStoreService;
    private final CountryService countryService;

    public UserProfileDetailsServiceImpl(UserProfileDetailsRepository userProfileDetailsRepository,
                                         ModelMapper modelMapper,
                                         FileStoreService fileStoreService,
                                         CountryService countryService) {
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.modelMapper = modelMapper;
        this.fileStoreService = fileStoreService;
        this.countryService = countryService;
    }


    @Override
    public UserProfileDetails createUserProfileDetails(String email) {
        UserProfileDetails userProfileDetails = new UserProfileDetails();
        userProfileDetails.setEmail(email);

        return this.userProfileDetailsRepository.saveAndFlush(userProfileDetails);
    }

    @Override
    @Transactional
    public UserProfileDetailsViewModel editUserProfileDetails(UserProfileDetailsEditBindingModel userProfileDetailsEditBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.findUserProfileDetailsById(userId);

        userProfileDetails.setFirstName(userProfileDetailsEditBindingModel.getFirstName());
        userProfileDetails.setLastName(userProfileDetailsEditBindingModel.getLastName());
        userProfileDetails.setPersonalWebsite(userProfileDetailsEditBindingModel.getPersonalWebsite());
        userProfileDetails.setDescription(userProfileDetailsEditBindingModel.getDescription());

        Country country = this.countryService.findCountryById(userProfileDetailsEditBindingModel.getCountry().getId());
        userProfileDetails.setCountry(country);
        userProfileDetails.getUser().setId(userId);

        if (userProfileDetails.getFirstName() != null &&
                userProfileDetails.getLastName() != null &&
                userProfileDetails.getCountry() != null) {
            userProfileDetails.setHasCompletedAccountSetup(true);
        }

        return this.modelMapper.map(this.userProfileDetailsRepository.save(userProfileDetails), UserProfileDetailsViewModel.class);
    }

    @Override
    public UserProfileDetails findUserProfileDetailsById(String userId) {
        return this.userProfileDetailsRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("UserProfileDetails not found with %s user id.", userId)));
    }

    @Override
    public void uploadUserProfileImage(String userId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image");
        }

        UserProfileDetails userProfileDetails = this.findUserProfileDetailsById(userId);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userId);
        String fileName = String.format("%s", file.getOriginalFilename());

        String key = String.format("%s/%s", userId, fileName);

        try {
            this.fileStoreService.save(path, fileName, Optional.of(metadata), file.getInputStream());
            userProfileDetails.setProfilePicture(AWS_S3_DEFAULT_URL + key);

            this.userProfileDetailsRepository.save(userProfileDetails);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<UserProfileDetailsAdminModel> findAllUsers() {
        return this.userProfileDetailsRepository.findAll()
                .stream()
                .map(userProfileDetails -> {
                    UserProfileDetailsAdminModel adminModel = this.modelMapper.map(userProfileDetails, UserProfileDetailsAdminModel.class);
                    adminModel.setUserId(userProfileDetails.getUser().getId());
                    adminModel.setAuthorities(userProfileDetails.getUser().getAuthorities().stream().map(Role::getAuthority).collect(Collectors.toList()));
                    adminModel.setUsername(userProfileDetails.getUser().getUsername());
                    return adminModel;
                })
                .collect(Collectors.toList());
    }
}