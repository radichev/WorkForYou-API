package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.EditUserProfileDetailsBindingModel;
import com.radichev.workforyou.model.viewModels.editUserProfileDetails.EditUserProfileDetailsViewModel;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user-profile-details")
public class UserProfileDetailsController {
    private final UserProfileDetailsService userProfileDetailsService;

    public UserProfileDetailsController(UserProfileDetailsService userProfileDetailsService) {
        this.userProfileDetailsService = userProfileDetailsService;
    }

    @GetMapping("/edit/{id}")
    public EditUserProfileDetailsViewModel getUserProfileDetails(@PathVariable String id){
        return this.userProfileDetailsService.getUserProfileDetails(id);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Void> editUserProfileDetails(@Valid @RequestBody EditUserProfileDetailsBindingModel editUserProfileDetailsBindingModel, @PathVariable String id) {
        this.userProfileDetailsService.editUserProfileDetails(editUserProfileDetailsBindingModel, id);
        return ResponseEntity.ok().build();
    }
}
