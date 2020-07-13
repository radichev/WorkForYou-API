package com.radichev.workforyou.repository;

import com.radichev.workforyou.domain.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, String> {

    Skill findBySkill(String skillName);
}
