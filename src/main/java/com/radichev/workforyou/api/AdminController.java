package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.ChangeRoleBindingModel;
import com.radichev.workforyou.model.viewModels.role.RoleAdminViewModel;
import com.radichev.workforyou.model.viewModels.userProfileDetails.UserProfileDetailsAdminModel;
import com.radichev.workforyou.service.RoleService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import com.radichev.workforyou.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserProfileDetailsService userProfileDetailsService;
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public AdminController(UserProfileDetailsService userProfileDetailsService,
                           UserService userService,
                           RoleService roleService,
                           ModelMapper modelMapper) {
        this.userProfileDetailsService = userProfileDetailsService;
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserProfileDetailsAdminModel>> findAllUsers() {
        return ResponseEntity
                .ok()
                .body(this.userProfileDetailsService.findAllUsers());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleAdminViewModel>> findAllRoles() {
        List<RoleAdminViewModel> roleAdminViewModels = this.roleService.findAllRoles()
                .stream()
                .map(role -> this.modelMapper.map(role, RoleAdminViewModel.class))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(roleAdminViewModels);
    }

    @PostMapping("/change-role")
    public ResponseEntity<Void> changeUserRole(@Valid @RequestBody ChangeRoleBindingModel changeRoleBindingModel) {
        this.userService.changeUserRole(changeRoleBindingModel);

        return ResponseEntity
                .ok()
                .build();
    }
}
