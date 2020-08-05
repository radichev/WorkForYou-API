package com.radichev.workforyou.api;

import com.radichev.workforyou.model.bindingModels.user.educationBindingModel.EducationBindingModel;
import com.radichev.workforyou.model.dtos.EducationDto.EducationDto;
import com.radichev.workforyou.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("api/educations")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Void> addEducations(@PathVariable String userId,
                                              @Valid @RequestBody EducationBindingModel educationBindingModel,
                                              UriComponentsBuilder ucBuilder) {

        EducationDto educationDto = this.educationService.addEducation(educationBindingModel, userId);

        return ResponseEntity
                .created(ucBuilder.path("/educations/{educationId}")
                        .buildAndExpand(educationDto.getId())
                        .toUri())
                        .build();
    }

    @PostMapping("/edit/{educationId}")
    public ResponseEntity<EducationDto> editEducationById(@PathVariable String educationId,
                                                          @Valid @RequestBody EducationBindingModel educationBindingModel) {

        return ResponseEntity
                .ok(this.educationService.editEducationById(educationId, educationBindingModel));
    }

    @DeleteMapping("/{educationId}")
    public ResponseEntity<Void> deleteEducationById(@PathVariable String educationId) {

        this.educationService.deleteEducationById(educationId);

        return ResponseEntity
                .ok()
                .build();
    }
}
