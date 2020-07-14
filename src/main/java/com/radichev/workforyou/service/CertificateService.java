package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.user.certificateBindingModel.CertificateBindingModel;

public interface CertificateService {

    void addCertificate(CertificateBindingModel certificateBindingModel, String userId);
}
