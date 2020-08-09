package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.auth.Role;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.repository.auth.RoleRepository;
import com.radichev.workforyou.service.serviceImpl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    private Role role;

    @BeforeEach
    public void setUp() {
        this.roleService = new RoleServiceImpl(roleRepository);

        role = new Role("User");
    }

    @Test
    public void testFindByAuthorityShouldReturnCorrectResult() {
        when(this.roleRepository.findByAuthority("testAuthority"))
                .thenReturn(Optional.ofNullable(role));

        Role testRole = this.roleService.findByAuthority("testAuthority");

        Assertions.assertEquals(role.getAuthority(), testRole.getAuthority());
    }

    @Test
    public void testFindByAuthorityShouldThrowEntityNotFoundExceptionWithInvalidAuthority() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.roleService.findByAuthority("testAuthority");
        });

        Assertions.assertEquals(exception.getMessage(), "Role testAuthority not found");
    }

    @Test
    public void testFindAllRolesShouldReturnCorrectResult() {
        when(this.roleRepository.findAll())
                .thenReturn(List.of(role));

        Set<Role> testRoleCollection = this.roleService.findAllRoles();
        Assertions.assertEquals(1, testRoleCollection.size());

        Role testRole = testRoleCollection.iterator().next();
        Assertions.assertEquals(role.getAuthority(), testRole.getAuthority());
    }
}
