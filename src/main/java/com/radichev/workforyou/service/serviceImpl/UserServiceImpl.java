package com.radichev.workforyou.service.serviceImpl;

import com.google.common.collect.Sets;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.model.bindingModels.auth.LoginBindingModel;
import com.radichev.workforyou.model.bindingModels.auth.RegisterBindingModel;
import com.radichev.workforyou.model.viewModels.auth.JwtViewModel;
import com.radichev.workforyou.model.viewModels.auth.RegisterViewModel;
import com.radichev.workforyou.exception.InvalidEntityException;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.security.jwt.JwtUtils;
import com.radichev.workforyou.service.RoleService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import com.radichev.workforyou.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;
    private final UserProfileDetailsService userProfileDetailsService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           ModelMapper modelMapper,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           JwtUtils jwtUtils, UserProfileDetailsService userProfileDetailsService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtils = jwtUtils;
        this.userProfileDetailsService = userProfileDetailsService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username))
                );
    }

    @Override
    @Transactional
    public RegisterViewModel register(RegisterBindingModel registerBindingModel) {

        this.userRepository.findByUsername(registerBindingModel.getUsername()).ifPresent(u -> {
            throw new InvalidEntityException(String.format("User with username '%s' already exists.", registerBindingModel.getUsername()));
        });

        User user = this.modelMapper.map(registerBindingModel, User.class);

        if (this.userRepository.count() == 0) {
            roleService.seedRolesInDB();

            user.setAuthorities(Sets.newHashSet(this.roleRepository.findAll()));
        } else {
            user.setAuthorities(Sets.newHashSet(this.roleRepository.findByAuthority("USER")));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user.setUserProfileDetails(this.userProfileDetailsService.createUserProfileDetails(registerBindingModel));

        this.userRepository.saveAndFlush(user);

        return this.modelMapper.map(user, RegisterViewModel.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public JwtViewModel loginUser(LoginBindingModel loginBindingModel) {

        final User user = findByUsername(loginBindingModel.getUsername())
                .orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", loginBindingModel.getUsername()))
        );

        final String token = jwtUtils.generateToken(user);


        return new JwtViewModel(token);
    }

}
