package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Certificate;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.model.bindingModels.user.certificateBindingModel.CertificateBindingModel;
import com.radichev.workforyou.repository.CertificateRepository;
import com.radichev.workforyou.service.CertificateService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;

    public CertificateServiceImpl(CertificateRepository certificateRepository, ModelMapper modelMapper, UserProfileDetailsService userProfileDetailsService) {
        this.certificateRepository = certificateRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
    }

    @Override
    public void addCertificate(CertificateBindingModel certificateBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        Certificate certificate = this.modelMapper.map(certificateBindingModel, Certificate.class);
        certificate.setUserProfileDetails(userProfileDetails);

        this.certificateRepository.saveAndFlush(certificate);
    }
}
