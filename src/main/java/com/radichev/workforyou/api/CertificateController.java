package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.user.certificateBindingModel.CertificateBindingModel;
import com.radichev.workforyou.model.dtos.CertificateDto.CertificateDto;
import com.radichev.workforyou.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("api/certificates")
@CrossOrigin(origins = "http://localhost:4200")
public class CertificateController {
    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<CertificateDto> addCertificate(@PathVariable String userId,
                                                         @Valid @RequestBody CertificateBindingModel certificateBindingModel,
                                                         UriComponentsBuilder ucBuilder) {

        CertificateDto certificateDto = this.certificateService.addCertificate(certificateBindingModel, userId);

        return ResponseEntity
                .created(ucBuilder.path("/certificates/{certificateId}")
                        .buildAndExpand(certificateDto.getId())
                        .toUri())
                        .build();
    }

    @PostMapping("/edit/{certificateId}")
    public ResponseEntity<CertificateDto> editCertificateById(@PathVariable String certificateId,
                                                              @Valid @RequestBody CertificateBindingModel certificateBindingModel) {

        return ResponseEntity
                .ok(this.certificateService.editCertificateById(certificateId, certificateBindingModel));
    }

    @DeleteMapping("/{certificateId}")
    public ResponseEntity<Void> deleteCertificateById(@PathVariable String certificateId) {

        this.certificateService.deleteCertificateById(certificateId);

        return ResponseEntity
                .ok()
                .build();
    }
}
