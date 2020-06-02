package com.radichev.workforyou.service.service.serviceImpl;

import com.google.common.collect.Sets;
import com.radichev.workforyou.domain.entity.auth.User;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.repository.auth.UserRepository;
import com.radichev.workforyou.service.model.UserServiceModel;
import com.radichev.workforyou.service.service.RoleService;
import com.radichev.workforyou.service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           ModelMapper modelMapper,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username))
                );
    }

    @Override
    public User register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        if (this.userRepository.count() == 0) {
            roleService.seedRolesInDB();

            user.setAuthorities(Sets.newHashSet(this.roleRepository.findAll()));
        } else {
            user.setAuthorities(Sets.newHashSet(this.roleRepository.findByAuthority("USER")));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return this.userRepository.saveAndFlush(user);
    }
}
