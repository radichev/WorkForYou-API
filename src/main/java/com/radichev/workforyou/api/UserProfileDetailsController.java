package com.radichev.workforyou.api;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.user.editUserProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.getUserProfileDetails.UserProfileDetailsViewModel;
import com.radichev.workforyou.model.viewModels.lookupViewModel.UserLookupTablesViewModel;
import com.radichev.workforyou.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("api/profile/details")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileDetailsController {
    private final UserProfileDetailsService userProfileDetailsService;
    private final SkillLevelService skillLevelService;
    private final LanguageLevelService languageLevelService;
    private final TitleTypeService titleTypeService;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    public UserProfileDetailsController(UserProfileDetailsService userProfileDetailsService,
                                        SkillLevelService skillLevelService,
                                        LanguageLevelService languageLevelService,
                                        TitleTypeService titleTypeService,
                                        CountryService countryService,
                                        ModelMapper modelMapper) {

        this.userProfileDetailsService = userProfileDetailsService;
        this.skillLevelService = skillLevelService;
        this.languageLevelService = languageLevelService;
        this.titleTypeService = titleTypeService;
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDetailsViewModel> getUserProfileDetails(@PathVariable String id) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(id);
        UserProfileDetailsViewModel userProfileDetailsViewModel = this.modelMapper.map(userProfileDetails, UserProfileDetailsViewModel.class);
        userProfileDetailsViewModel.setUsername(userProfileDetails.getUser().getUsername());
        userProfileDetailsViewModel.setUserId(userProfileDetails.getUser().getId());
        return ResponseEntity
                .ok()
                .body(userProfileDetailsViewModel);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserProfileDetailsViewModel> editUserProfileDetails(@Valid @RequestBody UserProfileDetailsEditBindingModel userProfileDetailsEditBindingModel,
                                                                              @PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(this.userProfileDetailsService.editUserProfileDetails(userProfileDetailsEditBindingModel, id));
    }

    @PostMapping(
            value = "/{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> uploadUserProfileImage(@PathVariable("id") String userId,
                                                       @RequestParam("file") MultipartFile file) {

        this.userProfileDetailsService.uploadUserProfileImage(userId, file);

        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/{id}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("id") String userId) {
        return this.userProfileDetailsService.downloadUserProfileImage(userId);
//        return ResponseEntity.ok().build();
    }

    @GetMapping("/lookups")
    public ResponseEntity<UserLookupTablesViewModel> getAllLookupTables() {
        UserLookupTablesViewModel userLookupTablesViewModel = new UserLookupTablesViewModel();
        userLookupTablesViewModel.setSkillLevels(this.skillLevelService.findAllSkillLevels());
        userLookupTablesViewModel.setLanguageLevels(this.languageLevelService.findAllLanguageLevels());
        userLookupTablesViewModel.setTitleTypes(this.titleTypeService.findAllTitleTypes());
        userLookupTablesViewModel.setCountries(this.countryService.findAllCountries());

        return ResponseEntity
                .ok()
                .body(userLookupTablesViewModel);
    }
}
