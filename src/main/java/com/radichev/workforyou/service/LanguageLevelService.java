package com.radichev.workforyou.service;

import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;

import java.util.List;

public interface LanguageLevelService {
    List<LanguageLevelDto> findAllLanguageLevels();

    void initLanguageLevels();
}
