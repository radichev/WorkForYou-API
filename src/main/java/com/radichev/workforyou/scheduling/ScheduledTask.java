package com.radichev.workforyou.scheduling;

import com.radichev.workforyou.service.JobService;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

public class ScheduledTask {
    private final JobService jobService;

    public ScheduledTask(JobService jobService) {
        this.jobService = jobService;
    }

    @Scheduled(cron = "0 0 3 ? * 4/7 *")
    private void DeleteOldJobsScheduledTask() {
        LocalDate yearAgo = LocalDate.now().minusYears(1);
        this.jobService.findAllJobs()
                .forEach(job -> {
                    if (job.getCreatedDate().isBefore(yearAgo) && job.getBoughtByUser().size() == 0) {
                        this.jobService.deleteJobById(job.getId());
                    }
                });
    }
}
