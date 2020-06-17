package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.UserProfileDetailsEditBindingModel;
import com.radichev.workforyou.model.viewModels.getUserProfileDetails.UserProfileDetailsViewModel;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
