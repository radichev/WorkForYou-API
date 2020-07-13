package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.certificateBindingModel.CertificateBindingModel;

public interface CertificateService {

    void addCertificate(CertificateBindingModel certificateBindingModel, String userId);
}
