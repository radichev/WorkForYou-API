package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.EditUserProfileDetailsBindingModel;
import com.radichev.workforyou.model.viewModels.editUserProfileDetails.EditUserProfileDetailsViewModel;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
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
    public ResponseEntity<Void> edit(@RequestBody EditUserProfileDetailsBindingModel editUserProfileDetailsBindingModel, @PathVariable String id) {
        this.userProfileDetailsService.editUserProfileDetails(editUserProfileDetailsBindingModel, id);
        return ResponseEntity.ok().build();
    }
}
