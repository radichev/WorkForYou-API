package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.certificateBindingModel.CertificateBindingModel;
import com.radichev.workforyou.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> addCertificate(@PathVariable String userId, @Valid @RequestBody CertificateBindingModel certificateBindingModel){
        this.certificateService.addCertificate(certificateBindingModel, userId);
        return ResponseEntity.ok().build();
    }
}
