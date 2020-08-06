package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Certificate;
import com.radichev.workforyou.domain.entity.UserProfileDetails;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.certificateBindingModel.CertificateBindingModel;
import com.radichev.workforyou.model.dtos.CertificateDto.CertificateDto;
import com.radichev.workforyou.repository.CertificateRepository;
import com.radichev.workforyou.service.serviceImpl.CertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CertificateServiceImplTest {

    private CertificateService certificateService;

    @Mock
    private CertificateRepository certificateRepository;

    @Mock
    private UserProfileDetailsService userProfileDetailsService;

    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    public void setUp() {
        this.certificateService = new CertificateServiceImpl(certificateRepository, modelMapper, userProfileDetailsService);
    }

    @Test
    public void testAddCertificateShouldReturnCorrectResult() {
        Certificate certificate = new Certificate();
        certificate.setCertificateSubject("Spring");
        certificate.setAwardedFrom("Softuni");
        certificate.setGraduationYear(2020);

        UserProfileDetails userProfileDetails = new UserProfileDetails();
        userProfileDetails.setFirstName("Ivan");
        userProfileDetails.setLastName("Petrov");
        userProfileDetails.setEmail("asd@abv.bg");

        when(this.certificateRepository.saveAndFlush(Mockito.any(Certificate.class)))
                .thenReturn(certificate);

        when(this.userProfileDetailsService.findUserProfileDetailsById("testId"))
                .thenReturn(userProfileDetails);

        CertificateDto certificateDto = this.certificateService
                .addCertificate(this.modelMapper.map(certificate, CertificateBindingModel.class), "testId");

        Assertions.assertEquals(certificate.getAwardedFrom(), certificateDto.getAwardedFrom());
        Assertions.assertEquals(certificate.getCertificateSubject(), certificateDto.getCertificateSubject());
        Assertions.assertEquals(certificate.getGraduationYear(), certificateDto.getGraduationYear());
    }

    @Test
    public void testFindCertificateByIdShouldReturnCorrectResult() {
        Certificate certificate = new Certificate();
        certificate.setCertificateSubject("Spring");
        certificate.setAwardedFrom("Softuni");
        certificate.setGraduationYear(2020);

        when(this.certificateRepository.findById("testId"))
                .thenReturn(Optional.of(certificate));

        Certificate testCertificate = this.certificateService.findCertificateById("testId");

        Assertions.assertEquals(certificate.getAwardedFrom(), testCertificate.getAwardedFrom());
        Assertions.assertEquals(certificate.getCertificateSubject(), testCertificate.getCertificateSubject());
        Assertions.assertEquals(certificate.getGraduationYear(), testCertificate.getGraduationYear());
    }

    @Test
    public void testFindCertificateByIdShouldThrowEntityNotFoundExceptionWithInvalidId() {
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            this.certificateService.findCertificateById("testId");
        });

        Assertions.assertEquals(exception.getMessage(), "Certificate not found with testId id.");
    }

    @Test
    public void testEditCertificateByIdShouldReturnCorrectResult() {
        Certificate certificate = new Certificate();
        certificate.setCertificateSubject("Spring");
        certificate.setAwardedFrom("Softuni");
        certificate.setGraduationYear(2020);

        when(this.certificateRepository.findById("testId"))
                .thenReturn(Optional.of(certificate));

        when(this.certificateRepository.save(Mockito.any(Certificate.class)))
                .thenReturn(certificate);

        CertificateBindingModel certificateBindingModel = new CertificateBindingModel();
        certificateBindingModel.setAwardedFrom("FMI");
        certificateBindingModel.setCertificateSubject("Databases");
        certificateBindingModel.setGraduationYear(1998);

        CertificateDto certificateDto = this.certificateService.editCertificateById("testId", certificateBindingModel);

        Assertions.assertEquals(certificateBindingModel.getCertificateSubject(), certificateDto.getCertificateSubject());
        Assertions.assertEquals(certificateBindingModel.getGraduationYear(), certificateDto.getGraduationYear());
        Assertions.assertEquals(certificateBindingModel.getAwardedFrom(), certificateDto.getAwardedFrom());
    }

    @Test
    public void testFindAllCertificatesShouldReturnCorrectResult(){
        Certificate certificate = new Certificate();
        certificate.setCertificateSubject("Spring");
        certificate.setAwardedFrom("Softuni");
        certificate.setGraduationYear(2020);

        when(this.certificateRepository.findAll())
                .thenReturn(List.of(certificate));

        List<CertificateDto> allCertificates = this.certificateService.findAllCertificates();

        Assertions.assertEquals(1, allCertificates.size());
        CertificateDto certificateDto = allCertificates.get(0);

        Assertions.assertEquals(certificate.getCertificateSubject(), certificateDto.getCertificateSubject());
        Assertions.assertEquals(certificate.getAwardedFrom(), certificateDto.getAwardedFrom());
        Assertions.assertEquals(certificate.getGraduationYear(), certificateDto.getGraduationYear());
    }


}
