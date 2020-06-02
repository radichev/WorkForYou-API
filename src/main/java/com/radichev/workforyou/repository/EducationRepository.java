package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, String> {
}
