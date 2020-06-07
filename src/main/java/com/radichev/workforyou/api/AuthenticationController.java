package com.radichev.workforyou.api;

import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.domain.model.bindingModels.auth.LoginBindingModel;
import com.radichev.workforyou.domain.model.bindingModels.auth.RegisterBindingModel;
import com.radichev.workforyou.domain.model.viewModels.auth.JwtViewModel;
import com.radichev.workforyou.domain.model.viewModels.auth.RegisterViewModel;
import com.radichev.workforyou.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterViewModel> createUser(@Valid @RequestBody RegisterBindingModel registerBindingModel) {
        RegisterViewModel created = this.userService.register(registerBindingModel);

        URI location = MvcUriComponentsBuilder.fromMethodName(AuthenticationController.class, "createUser", User.class)
                .pathSegment("{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtViewModel> login(@Valid @RequestBody LoginBindingModel loginBindingModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginBindingModel.getUsername(), loginBindingModel.getPassword()));

        return ResponseEntity.ok(this.userService.loginUser(loginBindingModel));
    }

}
