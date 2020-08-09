package com.radichev.workforyou;

import com.radichev.workforyou.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {
    private final LanguageLevelService languageLevelService;
    private final SkillLevelService skillLevelService;
    private final TitleTypeService titleTypeService;
    private final CountryService countryService;
    private final WorkSphereService workSphereService;

    public CommandRunner(LanguageLevelService languageLevelService, SkillLevelService skillLevelService, TitleTypeService titleTypeService, CountryService countryService, WorkSphereService workSphereService) {
        this.languageLevelService = languageLevelService;
        this.skillLevelService = skillLevelService;
        this.titleTypeService = titleTypeService;
        this.countryService = countryService;
        this.workSphereService = workSphereService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.languageLevelService.initLanguageLevels();
        this.skillLevelService.initSkillLevels();
        this.titleTypeService.initTitleTypes();
        this.countryService.initCountries();
        this.workSphereService.initWorkSpheres();
    }
}
