package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.TitleType;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;

import java.util.List;

public interface TitleTypeService {
    void initTitleTypes();

    List<TitleTypeDto> findAllTitleTypes();

    TitleType findByTitleType(String titleType);
}
