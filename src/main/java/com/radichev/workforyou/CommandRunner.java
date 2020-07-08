package com.radichev.workforyou;

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

    public CommandRunner(LanguageLevelService languageLevelService, SkillLevelService skillLevelService, TitleTypeService titleTypeService) {
        this.languageLevelService = languageLevelService;
        this.skillLevelService = skillLevelService;
        this.titleTypeService = titleTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.languageLevelService.initLanguageLevels();
        this.skillLevelService.initSkillLevels();
        this.titleTypeService.initTitleTypes();
    }
}
