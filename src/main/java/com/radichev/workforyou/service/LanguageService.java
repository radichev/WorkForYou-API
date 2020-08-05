package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Language;
import com.radichev.workforyou.model.bindingModels.user.languageBindingModel.LanguageBindingModel;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageDto;

public interface LanguageService {

    LanguageDto addLanguage(LanguageBindingModel languageBindingModel, String userId);

    Language findLanguageById(String languageId);

    void deleteLanguageById(String languageId);
}
