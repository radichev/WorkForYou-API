package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.UserProfileDetailsBindingModel;
import com.radichev.workforyou.service.UserProfileDetailsService;
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
