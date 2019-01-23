package com.yjl.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public abstract class AbstractTask implements Job {


    protected abstract void executeInternal(JobExecutionContext context);

    protected String cronExpression;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            executeInternal(context);
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            //logger.error("job execute failed!");
        }
    }

    public String getCronExpression() {
        return cronExpression;
    }
}
