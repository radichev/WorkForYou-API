package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.auth.Role;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(this.roleRepository.findAll());
    }

    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepository.findByAuthority(authority)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Role %s not found", authority)));
    }
}
