package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.user.certificateBindingModel.CertificateBindingModel;
import com.radichev.workforyou.model.dtos.CertificateDto.CertificateDto;

public interface CertificateService {

    CertificateDto addCertificate(CertificateBindingModel certificateBindingModel, String userId);
}
