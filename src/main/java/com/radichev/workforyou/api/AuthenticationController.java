package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.auth.SignInBindingModel;
import com.radichev.workforyou.model.bindingModels.auth.SignUpBindingModel;
import com.radichev.workforyou.model.viewModels.auth.SignInViewModel;
import com.radichev.workforyou.model.viewModels.auth.SignUpViewModel;
import com.radichev.workforyou.service.UserProfileDetailsService;
import com.radichev.workforyou.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/authentication")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, UserProfileDetailsService userProfileDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpViewModel> signUp(@Valid @RequestBody SignUpBindingModel signUpBindingModel) {
        SignUpViewModel created = this.userService.signUpUser(signUpBindingModel);

        return ResponseEntity.ok().body(created);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInViewModel> signIn(@Valid @RequestBody SignInBindingModel signInBindingModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInBindingModel.getUsername(), signInBindingModel.getPassword()));

        return ResponseEntity.ok(this.userService.signInUser(signInBindingModel));
    }

}
