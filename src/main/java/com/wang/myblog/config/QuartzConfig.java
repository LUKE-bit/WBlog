package com.wang.myblog.config;


import com.wang.myblog.job.QuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    private static final String LIKE_TASK_IDENTITY = "LikeTask";
    private static final String LIKE_COUNT_TASK_IDENTITY = "LikeCountTask";

    @Bean
    public JobDetail likeJobDetail(){
        return JobBuilder.newJob(QuartzJob.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger likeCountJobTrigger(){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 1/1 * ? ");
        return TriggerBuilder.newTrigger().forJob(likeJobDetail()).withIdentity(LIKE_COUNT_TASK_IDENTITY).withSchedule(cronScheduleBuilder).build();
    }
}
