package com.yjl.quartz;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component("testTask")
public class TestTask extends AbstractTask {

    //private Logger logger = LoggerFactory.getLogger(TestTask.class);

    @PostConstruct
    public void init() {
        this.cronExpression = "0/2 * * * * ? ";
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        System.out.println("test task start execute.");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("test task execute interrupted.");
        }
        System.out.println("test task execute end.");
    }
}
