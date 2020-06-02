package com.radichev.workforyou.api;

import com.radichev.workforyou.service.model.UserServiceModel;
import com.radichev.workforyou.service.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerNewStudent(@RequestBody UserServiceModel userServiceModel) {
        userService.register(userServiceModel);
    }

}
