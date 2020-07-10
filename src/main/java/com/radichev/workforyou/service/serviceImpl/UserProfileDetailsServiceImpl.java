package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.bucket.BucketName;
import com.radichev.workforyou.domain.entity.Language;
import com.radichev.workforyou.domain.entity.Skill;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.getUserProfileDetails.UserProfileDetailsViewModel;
import com.radichev.workforyou.repository.SkillLevelRepository;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final FileStoreImpl fileStore;
    private final CountryService countryService;
    private final LanguageLevelService languageLevelService;
    private final SkillLevelService skillLevelService;
    private final SkillLevelRepository skillLevelRepository;
    private final TitleTypeService titleTypeService;

    public UserProfileDetailsServiceImpl(UserProfileDetailsRepository userProfileDetailsRepository,
                                         ModelMapper modelMapper,
                                         @Lazy UserService userService,
                                         FileStoreImpl fileStore,
                                         CountryService countryService,
                                         LanguageLevelService languageLevelService,
                                         SkillLevelService skillLevelService,
                                         SkillLevelRepository skillLevelRepository, TitleTypeService titleTypeService) {
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.fileStore = fileStore;
        this.countryService = countryService;
        this.languageLevelService = languageLevelService;
        this.skillLevelService = skillLevelService;
        this.skillLevelRepository = skillLevelRepository;
        this.titleTypeService = titleTypeService;
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

//        Set<Education> educations = userProfileDetails.getEducations();
////
//        educations
//                .forEach(education -> {
//                    education.setCountry(this.countryService.findByCountryName(education.getCountry().getCountry()));
//                    education.setTitleType(this.titleTypeService.findByTitleType(education.getTitleType().getTitleType()));
//                    System.out.println();
//                });
////
//        userProfileDetails.setEducations(educations);
        System.out.println();
//
        Set<Language> languages = userProfileDetails.getLanguages();

        languages
                .forEach(language -> {
                    language.setLanguageLevel(this.languageLevelService.findByLanguageLevel(language.getLanguage()));

                });

        userProfileDetails.setLanguages(languages);
//
        Set<Skill> skills = userProfileDetails
                .getSkills()
                .stream()
                .peek(skill -> {
                    skill.setSkillLevel(this.skillLevelService.findBySkillLevel(skill.getSkill()));

                }).collect(Collectors.toSet());
//
        userProfileDetails.setSkills(skills);

        System.out.println();

        this.userProfileDetailsRepository.save(userProfileDetails);
    }

    @Override
    public UserProfileDetailsViewModel getEditUserProfileDetails(String id) {
        return this.modelMapper.map(this.userService.findUserProfileDetailsById(id), UserProfileDetailsViewModel.class);
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