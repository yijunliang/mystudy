package com.yjl.quartz;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

public class Test {
	
	
	public static void main(String[] args) throws InterruptedException {
    QuartzManager quartzManager = new QuartzManager();
	quartzManager.start();
    TimeUnit.SECONDS.sleep(10);
    System.out.println("start update job");
    //修改任务
    quartzManager.updateJob("testTask", "0/3 * * * * ? ");
    System.out.println("end update job");
    TimeUnit.SECONDS.sleep(10);
    System.out.println("start delete job");
    //删除任务
    quartzManager.deleteJob("testTask");
    System.out.println("end delete job");
    TimeUnit.SECONDS.sleep(10);
    /*//添加任务
    System.out.println("start add job");
    quartzManager.addJob("testTask", testTask.getClass().getName(), "0/3 * * * * ?");
    System.out.println("end add job");
    TimeUnit.SECONDS.sleep(10);
    //修改任务
    System.out.println("start update job");
    quartzManager.updateJob("testTask", "0/3 * * * * ?");
    System.out.println("end update job");
    TimeUnit.SECONDS.sleep(10);
    //删除任务
    System.out.println("start delete job");
    quartzManager.deleteJob("testTask");
    System.out.println("end delete job");
    System.out.println("end.");*/
    
	}

}
