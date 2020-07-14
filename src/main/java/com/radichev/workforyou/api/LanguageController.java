package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.user.languageBindingModel.LanguageBindingModel;
import com.radichev.workforyou.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/languages")
@CrossOrigin(origins = "http://localhost:4200")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Void> addLanguage(@PathVariable String userId, @Valid @RequestBody LanguageBindingModel languageBindingModel){
        this.languageService.addLanguage(languageBindingModel, userId);
        return ResponseEntity.ok().build();
    }
}
