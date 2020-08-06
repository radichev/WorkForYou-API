package com.radichev.workforyou.service.serviceImpl;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String principalName;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            principalName = "anonymousUser";
        }
        return Optional.ofNullable(principalName);
    }

}
