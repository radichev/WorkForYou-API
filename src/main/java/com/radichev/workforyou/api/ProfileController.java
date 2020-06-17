package com.radichev.workforyou.api;

import com.radichev.workforyou.model.viewModels.editUserProfileDetails.EditUserProfileDetailsViewModel;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/profile")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {
    private final UserProfileDetailsService userProfileDetailsService;

    public ProfileController(UserProfileDetailsService userProfileDetailsService) {
        this.userProfileDetailsService = userProfileDetailsService;
    }

    @GetMapping("/user/{id}")
    public EditUserProfileDetailsViewModel getUserProfileDetails(@PathVariable String id){
        return this.userProfileDetailsService.getEditUserProfileDetails(id);
    }
}
