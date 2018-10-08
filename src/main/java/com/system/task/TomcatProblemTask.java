package com.system.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
@EnableScheduling
@EnableAsync
public class TomcatProblemTask {
	private static Logger logger = Logger.getLogger(TomcatProblemTask.class);
	
	@Async
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void taskExecutor(){
		logger.info("检查定时任务是否执行了两次！！！！");
	}
	
}
