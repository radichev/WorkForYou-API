package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.EditUserProfileDetailsBindingModel;
import com.radichev.workforyou.model.viewModels.editUserProfileDetails.EditUserProfileDetailsViewModel;
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
    public EditUserProfileDetailsViewModel editUserProfileDetails(@PathVariable String id){
        return this.userProfileDetailsService.getEditUserProfileDetails(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> editUserProfileDetails(@Valid @RequestBody EditUserProfileDetailsBindingModel editUserProfileDetailsBindingModel, @PathVariable String id) {
        this.userProfileDetailsService.editUserProfileDetails(editUserProfileDetailsBindingModel, id);
        return ResponseEntity.ok().build();
    }
}
