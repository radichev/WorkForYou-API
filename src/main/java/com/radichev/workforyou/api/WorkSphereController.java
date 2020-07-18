package com.radichev.workforyou.api;

import com.radichev.workforyou.model.viewModels.lookupViewModel.WorkSphereLookupViewModel;
import com.radichev.workforyou.service.WorkSphereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/work-spheres")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkSphereController {
    private final WorkSphereService workSphereService;

    public WorkSphereController(WorkSphereService workSphereService) {
        this.workSphereService = workSphereService;
    }


    @GetMapping("/all")
    public ResponseEntity<WorkSphereLookupViewModel> getAllWorkSpheres() {
        WorkSphereLookupViewModel workSphereLookupViewModel = new WorkSphereLookupViewModel();
        workSphereLookupViewModel.setWorkSpheres(this.workSphereService.findAllWorkSpheres());

        return ResponseEntity.ok().body(workSphereLookupViewModel);
    }
}
