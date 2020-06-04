package com.radichev.workforyou.api;

import com.radichev.workforyou.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllJobs() {
        System.out.println("getAllJobs");
        return "Succeeded";
    }
}
