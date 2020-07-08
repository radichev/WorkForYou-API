package com.radichev.workforyou.api;

import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;
import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;
import com.radichev.workforyou.service.SkillLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/skills")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {
    private final SkillLevelService skillLevelService;

    public SkillController(SkillLevelService skillLevelService) {
        this.skillLevelService = skillLevelService;
    }

    @GetMapping("/levels/all")
    public ResponseEntity<List<SkillLevelDto>> findAllLanguageLevels(){
        return ResponseEntity.ok().body(this.skillLevelService.findAllSkillLevels());
    }
}
