package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, String> {
}
