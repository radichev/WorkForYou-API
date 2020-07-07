package com.radichev.workforyou.service;

import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;

import java.util.List;

public interface SkillLevelService {

    List<SkillLevelDto> findAllSkillLevels();

    void initSkillLevels();
}
