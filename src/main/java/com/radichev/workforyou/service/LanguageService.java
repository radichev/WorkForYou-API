package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.user.languageBindingModel.LanguageBindingModel;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageDto;

public interface LanguageService {

    LanguageDto addLanguage(LanguageBindingModel languageBindingModel, String userId);

}
