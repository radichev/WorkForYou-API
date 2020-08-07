package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.auth.Role;

import java.util.Set;

public interface RoleService {
    void seedRolesInDB();

    Set<Role> findAllRoles();

    Role findByAuthority(String authority);
}
