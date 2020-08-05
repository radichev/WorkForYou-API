package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.user.languageBindingModel.LanguageBindingModel;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageDto;
import com.radichev.workforyou.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Void> addLanguage(@PathVariable String userId,
                                            @Valid @RequestBody LanguageBindingModel languageBindingModel,
                                            UriComponentsBuilder ucBuilder) {

        LanguageDto languageDto = this.languageService.addLanguage(languageBindingModel, userId);

        return ResponseEntity
                .created(ucBuilder.path("/languages/{languageId}")
                        .buildAndExpand(languageDto.getId())
                        .toUri())
                    .build();
    }

    @PostMapping("/edit/{languageId}")
    public ResponseEntity<LanguageDto> editLanguageById(@PathVariable String languageId,
                                                        @Valid @RequestBody LanguageBindingModel languageBindingModel) {

        return ResponseEntity
                .ok(this.languageService.editLanguageById(languageId, languageBindingModel));
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<Void> deleteLanguageById(@PathVariable String languageId) {

        this.languageService.deleteLanguageById(languageId);

        return ResponseEntity
                .ok()
                .build();
    }
}
