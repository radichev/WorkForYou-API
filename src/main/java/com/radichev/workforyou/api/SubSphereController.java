package com.radichev.workforyou.api;

import com.radichev.workforyou.model.dtos.WorkSphereDto.SubSphereDto;
import com.radichev.workforyou.service.SubSphereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sub-spheres")
@CrossOrigin(origins = "http://localhost:4200")
public class SubSphereController {
    private final SubSphereService subSphereService;

    public SubSphereController(SubSphereService subSphereService) {
        this.subSphereService = subSphereService;
    }

    @GetMapping("/five")
    public ResponseEntity<List<SubSphereDto>> findFiveSubSpheres(){
        return ResponseEntity
                .ok()
                .body(this.subSphereService.findFiveSubSpheres());
    }
}
