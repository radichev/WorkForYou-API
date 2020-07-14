package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.user.skillBindingModel.SkillBindingModel;
import com.radichev.workforyou.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/skills")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;

    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Void> addSkill(@PathVariable String userId, @Valid @RequestBody SkillBindingModel skillBindingModel){
        this.skillService.addSkill(skillBindingModel, userId);
        return ResponseEntity.ok().build();
    }
}
