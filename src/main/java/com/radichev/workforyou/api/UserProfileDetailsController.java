package com.radichev.workforyou.api;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
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

    @GetMapping("/edit/{id}")
    public UserProfileDetails getUserProfileDetails(@PathVariable String id){
        return this.userProfileDetailsService.getUserProfileDetails(id);
    }

    @PostMapping("/edit/{id}")
    public String edit(@RequestBody UserProfileDetailsBindingModel userProfileDetailsBindingModel, @PathVariable String id) {
        this.userProfileDetailsService.editUserProfileDetails(userProfileDetailsBindingModel, id);
        return "Succeeded";
    }
}
