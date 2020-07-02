package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.getUserProfileDetails.UserProfileDetailsViewModel;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.springframework.http.HttpStatus;
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

    public UserProfileDetailsController(UserProfileDetailsService userProfileDetailsService) {
        this.userProfileDetailsService = userProfileDetailsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDetailsViewModel> getUserProfileDetails(@PathVariable String id){
        return ResponseEntity.ok(this.userProfileDetailsService.getEditUserProfileDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editUserProfileDetails(@Valid @RequestBody UserProfileDetailsEditBindingModel userProfileDetailsEditBindingModel, @PathVariable String id) {
        this.userProfileDetailsService.editUserProfileDetails(userProfileDetailsEditBindingModel, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(
            value = "/{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> uploadUserProfileImage(@PathVariable("id") String userId,
                                                       @RequestParam("file") MultipartFile file) {

        this.userProfileDetailsService.uploadUserProfileImage(userId, file);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("id") String userId) {
        return this.userProfileDetailsService.downloadUserProfileImage(userId);
//        return ResponseEntity.ok().build();
    }
}
