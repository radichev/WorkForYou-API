package com.radichev.workforyou.api;

import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.domain.entity.auth.Role;
import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.model.bindingModels.ChangeRoleBindingModel;
import com.radichev.workforyou.repository.UserProfileDetailsRepository;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.service.RoleService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import com.radichev.workforyou.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Set;

import static com.radichev.workforyou.api.TestUtils.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(value = "testUser", authorities = {"ADMIN"})
public class AdminControllerTest {

    private final MockMvc mockMvc;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final UserProfileDetailsService userProfileDetailsService;
    private final UserProfileDetailsRepository userProfileDetailsRepository;

    @Autowired
    public AdminControllerTest(MockMvc mockMvc,
                               UserService userService,
                               UserRepository userRepository,
                               RoleService roleService,
                               RoleRepository roleRepository,
                               UserProfileDetailsService userProfileDetailsService,
                               UserProfileDetailsRepository userProfileDetailsRepository) {
        this.mockMvc = mockMvc;
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.userProfileDetailsService = userProfileDetailsService;
        this.userProfileDetailsRepository = userProfileDetailsRepository;
    }

    private String USER_ID;
    private User user;
    private Role adminRole;

    @BeforeEach
    public void setUp() {
        this.userRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.userProfileDetailsRepository.deleteAll();

        UserProfileDetails userProfileDetails = new UserProfileDetails();
        userProfileDetails.setEmail("testEmail@abv.bg");
        userProfileDetails.setFirstName("testFirstName");
        userProfileDetails.setLastName("testLastName");
        userProfileDetails = this.userProfileDetailsRepository.saveAndFlush(userProfileDetails);

        Role role = new Role("USER");
        role = this.roleRepository.save(role);

        adminRole = new Role("ADMIN");
        adminRole = this.roleRepository.save(role);

        user = new User();
        user.setUsername("testUsername");
        user.setPassword("testPassword");
        user.setAuthorities(Set.of(role));
        user.setUserProfileDetails(userProfileDetails);
        user = this.userRepository.save(user);
        USER_ID = user.getId();
    }

    @Test
    public void testFindAllUsersShouldReturnCorrectResult() throws Exception {
        this.mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(user.getUserProfileDetails().getId())))
                .andExpect(jsonPath("$[0].userId", is(user.getId())))
                .andExpect(jsonPath("$[0].username", is(user.getUsername())))
                .andExpect(jsonPath("$[0].firstName", is(user.getUserProfileDetails().getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(user.getUserProfileDetails().getLastName())))
                .andExpect(jsonPath("$[0].authorities", hasSize(user.getAuthorities().size())));
    }

    @Test
    public void testFindAllRolesShouldReturnCorrectResult() throws Exception {
        this.mockMvc.perform(get("/api/admin/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(user.getAuthorities().size())));
    }

    @Test
    public void testChangeUserRoleShouldPerformCorrect() throws Exception {
        ChangeRoleBindingModel changeRoleBindingModel = new ChangeRoleBindingModel();
        changeRoleBindingModel.setAuthority(adminRole.getAuthority());
        changeRoleBindingModel.setUsername(user.getUsername());

        User testUser = this.userRepository.findByUsername(changeRoleBindingModel.getUsername()).get();
        this.mockMvc.perform(post("/api/admin/change-role")
                .content(asJsonString(changeRoleBindingModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Assertions.assertEquals(user.getAuthorities().iterator().next().getAuthority(), testUser.getAuthorities().iterator().next().getAuthority());
    }
}
