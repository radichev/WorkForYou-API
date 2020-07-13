package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.LanguageLevel;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;

import java.util.List;

public interface LanguageLevelService {
    void initLanguageLevels();

    List<LanguageLevelDto> findAllLanguageLevels();

    LanguageLevel findByLanguageLevel(String languageLevel);

    LanguageLevel findLanguageLevelById(String languageLevelId);
}
