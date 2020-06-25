package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.auth.Role;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDB() {
        this.roleRepository.saveAndFlush(new Role("ADMIN"));
        this.roleRepository.saveAndFlush(new Role("MODERATOR"));
        this.roleRepository.saveAndFlush(new Role("USER"));
    }
}
