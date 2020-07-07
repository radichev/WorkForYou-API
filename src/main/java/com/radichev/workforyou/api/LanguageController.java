package com.radichev.workforyou.api;

import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;
import com.radichev.workforyou.service.LanguageLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/languages")
public class LanguageController {
    private final LanguageLevelService languageLevelService;

    public LanguageController(LanguageLevelService languageLevelService) {
        this.languageLevelService = languageLevelService;
    }

    @GetMapping("/levels/all")
    public ResponseEntity<List<LanguageLevelDto>> findAllLanguageLevels(){
        return ResponseEntity.ok().body(this.languageLevelService.findAllLanguageLevels());
    }
}
