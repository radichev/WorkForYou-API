package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.bucket.BucketName;
import com.radichev.workforyou.domain.entity.Country;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.user.editUserProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.getUserProfileDetails.UserProfileDetailsViewModel;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final FileStoreServiceImpl fileStore;
    private final CountryService countryService;

    public UserProfileDetailsServiceImpl(UserProfileDetailsRepository userProfileDetailsRepository,
                                         ModelMapper modelMapper,
                                         @Lazy UserService userService,
                                         FileStoreServiceImpl fileStore,
                                         CountryService countryService) {
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.fileStore = fileStore;
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
    public void editUserProfileDetails(UserProfileDetailsEditBindingModel userProfileDetailsEditBindingModel, String id) {
        UserProfileDetails userProfileDetails = this.userService.findUserProfileDetailsById(id);

        this.modelMapper.map(userProfileDetailsEditBindingModel, userProfileDetails);

        Country country = this.countryService.findCountryById(userProfileDetailsEditBindingModel.getCountry().getId());
        userProfileDetails.setCountry(country);

        this.userProfileDetailsRepository.save(userProfileDetails);
    }

    @Override
    public UserProfileDetailsViewModel getEditUserProfileDetails(String id) {
        return this.modelMapper.map(this.userService.findUserProfileDetailsById(id), UserProfileDetailsViewModel.class);
    }

    @Override
    public UserProfileDetails findUserProfileDetailsById(String userId) {
        return this.userService.findUserProfileDetailsById(userId);
    }

    @Override
    public void uploadUserProfileImage(String userId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image");
        }

        UserProfileDetails userProfileDetails = this.userService.findUserProfileDetailsById(userId);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userId);
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            this.fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            userProfileDetails.setProfilePicture(fileName);

            this.userProfileDetailsRepository.save(userProfileDetails);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public byte[] downloadUserProfileImage(String userId) {
        UserProfileDetails userProfileDetails = this.userService.findUserProfileDetailsById(userId);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userId);

        return this.fileStore.download(path, userProfileDetails.getProfilePicture());
    }
}