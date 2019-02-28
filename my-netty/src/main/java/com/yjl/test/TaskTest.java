package com.yjl.test;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.ScheduledFuture;

/**
 * 任务调度测试
 * @author Administrator
 *
 */
public class TaskTest {
	
	public static void main(String[] args) {
		
		Executor eventLoop = new Executor() {
			
			public void execute(Runnable command) {
				
			}
		};
		//EventLoop eventLoop1 = new NioEventLoop(null, eventLoop , null, null);
		
		Channel ch = new NioSocketChannel();
		
		ScheduledFuture<?> future = ch.eventLoop().scheduleAtFixedRate(new Runnable(){

			public void run() {
				System.out.println("每个60秒执行");
			}
			
		}, 60, 60, TimeUnit.SECONDS);
		
	}

}
