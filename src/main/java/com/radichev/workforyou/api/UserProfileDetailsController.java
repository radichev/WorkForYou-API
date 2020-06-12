package com.radichev.workforyou.api;

import com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.UserProfileDetailsBindingModel;
import com.radichev.workforyou.service.UserProfileDetailsService;
import com.radichev.workforyou.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserProfileDetailsController {
    private final UserProfileDetailsService userProfileDetailsService;

    public UserProfileDetailsController(UserProfileDetailsService userProfileDetailsService) {
        this.userProfileDetailsService = userProfileDetailsService;
    }

    @PostMapping("/edit")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String edit(@RequestBody UserProfileDetailsBindingModel userProfileDetailsBindingModel) {
        this.userProfileDetailsService.editUserProfileDetails(userProfileDetailsBindingModel);
        return "Succeeded";
    }
}
