package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.user.skillBindingModel.SkillBindingModel;
import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;
import com.radichev.workforyou.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Void> addSkill(@PathVariable String userId,
                                         @Valid @RequestBody SkillBindingModel skillBindingModel,
                                         UriComponentsBuilder ucBuilder) {

        SkillDto skillDto = this.skillService.addSkill(skillBindingModel, userId);

        return ResponseEntity
                .created(ucBuilder.path("/skills/{skillId}")
                        .buildAndExpand(skillDto.getId())
                        .toUri())
                        .build();
    }

    @PostMapping("/edit/{skillId}")
    public ResponseEntity<SkillDto> editSkillById(@PathVariable String skillId,
                                                  @Valid @RequestBody SkillBindingModel skillBindingModel) {

        return ResponseEntity
                .ok(this.skillService.editSkillById(skillId, skillBindingModel));
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkillById(@PathVariable String skillId) {

        this.skillService.deleteSkillById(skillId);

        return ResponseEntity
                .ok()
                .build();
    }
}
