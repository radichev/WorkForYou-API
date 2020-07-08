package com.radichev.workforyou;

import com.radichev.workforyou.service.CountryService;
import com.radichev.workforyou.service.LanguageLevelService;
import com.radichev.workforyou.service.SkillLevelService;
import com.radichev.workforyou.service.TitleTypeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {
    private final LanguageLevelService languageLevelService;
    private final SkillLevelService skillLevelService;
    private final TitleTypeService titleTypeService;
    private final CountryService countryService;

    public CommandRunner(LanguageLevelService languageLevelService, SkillLevelService skillLevelService, TitleTypeService titleTypeService, CountryService countryService) {
        this.languageLevelService = languageLevelService;
        this.skillLevelService = skillLevelService;
        this.titleTypeService = titleTypeService;
        this.countryService = countryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.languageLevelService.initLanguageLevels();
        this.skillLevelService.initSkillLevels();
        this.titleTypeService.initTitleTypes();
        this.countryService.initCountries();
    }
}
