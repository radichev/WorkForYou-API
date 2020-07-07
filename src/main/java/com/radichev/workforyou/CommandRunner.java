package com.radichev.workforyou;

import com.radichev.workforyou.service.LanguageLevelService;
import com.radichev.workforyou.service.SkillLevelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {
    private final LanguageLevelService languageLevelService;
    private final SkillLevelService skillLevelService;

    public CommandRunner(LanguageLevelService languageLevelService, SkillLevelService skillLevelService) {
        this.languageLevelService = languageLevelService;
        this.skillLevelService = skillLevelService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.languageLevelService.initLanguageLevels();
        this.skillLevelService.initSkillLevels();
    }
}
