package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.SkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, String> {
}
