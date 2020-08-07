package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.exception.InvalidEntityException;
import com.radichev.workforyou.model.bindingModels.auth.SignInBindingModel;
import com.radichev.workforyou.model.viewModels.auth.SignInViewModel;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.security.jwt.JwtUtils;
import com.radichev.workforyou.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserProfileDetailsService userProfileDetailsService;

    private ModelMapper modelMapper = new ModelMapper();
    private User userDetails;

    @BeforeEach
    public void setUp() {
        this.userService = new UserServiceImpl(userRepository,
                                               roleService,
                                               modelMapper,
                                               bCryptPasswordEncoder,
                                               jwtUtils,
                                               userProfileDetailsService);

        userDetails = new User();
        userDetails.setUsername("testUsername");
        userDetails.setPassword("123");
    }

    @Test
    public void testLoadUserByUsernameShouldReturnCorrectResult() {
        when(this.userRepository.findByUsername("testUsername"))
                .thenReturn(Optional.ofNullable(userDetails));

        UserDetails testUserDetails = this.userService.loadUserByUsername("testUsername");

        Assertions.assertEquals(userDetails.getUsername(), testUserDetails.getUsername());
        Assertions.assertEquals(userDetails.getPassword(), testUserDetails.getPassword());
    }

    @Test
    public void testLoadByUsernameShouldThrowExceptionWithInvalidUsername() {
        Exception exception = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            this.userService.loadUserByUsername(userDetails.getUsername());
        });

        Assertions.assertEquals(exception.getMessage(), "Username testUsername not found");
    }

    @Test
    public void testFindByUsernameShouldThrowExceptionWithInvalidUsername() {
        when(this.userRepository.findByUsername("duplicateUsername"))
                .thenThrow(InvalidEntityException.class);

        Assertions.assertThrows(InvalidEntityException.class, () -> {
            this.userRepository.findByUsername("duplicateUsername");
        });
    }

    @Test
    public void testFindByUsernameShouldReturnCorrectResult() {
        when(this.userRepository.findByUsername("testUsername"))
                .thenReturn(Optional.ofNullable(userDetails));

        User testUserDetails = this.userService.findByUsername("testUsername").get();

        Assertions.assertEquals(userDetails.getUsername(), testUserDetails.getUsername());
        Assertions.assertEquals(userDetails.getPassword(), testUserDetails.getPassword());
    }

    @Test
    public void testSignInUserShouldReturnCorrectResult() {
        String jwtToken = "testJwtToken";

        when(jwtUtils.generateToken(userDetails))
                .thenReturn(jwtToken);

        when(this.userService.findByUsername("testUsername"))
                .thenReturn(Optional.ofNullable(userDetails));

        SignInViewModel testSignInViewModel = this.userService
                .signInUser(this.modelMapper.map(userDetails, SignInBindingModel.class));

        Assertions.assertEquals(testSignInViewModel.getJwt(), jwtToken);
    }

    @Test
    public void testSignInUserShouldThrowExceptionWithInvalidUsername() {
        Exception exception = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            this.userService.signInUser(this.modelMapper.map(userDetails, SignInBindingModel.class));
        });

        Assertions.assertEquals(exception.getMessage(), "Username testUsername not found");
    }
}
