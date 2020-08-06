package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.Certificate;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.certificateBindingModel.CertificateBindingModel;
import com.radichev.workforyou.model.dtos.CertificateDto.CertificateDto;
import com.radichev.workforyou.repository.CertificateRepository;
import com.radichev.workforyou.service.CertificateService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;

    public CertificateServiceImpl(CertificateRepository certificateRepository,
                                  ModelMapper modelMapper,
                                  UserProfileDetailsService userProfileDetailsService) {
        this.certificateRepository = certificateRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
    }

    @Override
    public CertificateDto addCertificate(CertificateBindingModel certificateBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        Certificate certificate = this.modelMapper.map(certificateBindingModel, Certificate.class);
        certificate.setUserProfileDetails(userProfileDetails);

        return this.modelMapper.map(this.certificateRepository.saveAndFlush(certificate), CertificateDto.class);
    }

    @Override
    public Certificate findCertificateById(String certificateId) {
        return this.certificateRepository.findById(certificateId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Certificate not found with %s id.", certificateId)));
    }


    @Override
    public void deleteCertificateById(String certificateId) {
        Certificate certificate = this.findCertificateById(certificateId);
        certificate.setDeleted(true);
        certificate.setDeletedOn(LocalDate.now());

        this.certificateRepository.save(certificate);
    }

    @Override
    public CertificateDto editCertificateById(String certificateId, CertificateBindingModel certificateBindingModel) {
        Certificate certificate = this.findCertificateById(certificateId);

        this.modelMapper.map(certificateBindingModel, certificate);

        return this.modelMapper.map(this.certificateRepository.save(certificate), CertificateDto.class);
    }

    @Override
    public List<CertificateDto> findAllCertificates() {
        return this.certificateRepository.findAll()
                .stream()
                .map(certificate -> this.modelMapper.map(certificate, CertificateDto.class))
                .collect(Collectors.toList());
    }
}
