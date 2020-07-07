package com.radichev.workforyou;

import com.radichev.workforyou.service.LanguageLevelService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {
    private final LanguageLevelService languageLevelService;

    public CommandRunner(LanguageLevelService languageLevelService) {
        this.languageLevelService = languageLevelService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.languageLevelService.initLanguageLevels();
    }
}
