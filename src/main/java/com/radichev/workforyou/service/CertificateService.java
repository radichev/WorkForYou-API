package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Certificate;
import com.radichev.workforyou.model.bindingModels.user.certificateBindingModel.CertificateBindingModel;
import com.radichev.workforyou.model.dtos.CertificateDto.CertificateDto;

import java.util.List;

public interface CertificateService {

    CertificateDto addCertificate(CertificateBindingModel certificateBindingModel, String userId);

    Certificate findCertificateById(String certificateId);

    void deleteCertificateById(String certificateId);

    CertificateDto editCertificateById(String certificateId, CertificateBindingModel certificateBindingModel);

    List<CertificateDto> findAllCertificates();
}
