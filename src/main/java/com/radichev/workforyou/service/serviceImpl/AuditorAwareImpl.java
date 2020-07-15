package com.radichev.workforyou.service.serviceImpl;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();

        return Optional.ofNullable(principalName);
    }

}
