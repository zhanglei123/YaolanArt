package com.system.task;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @description:IVR发送拨号，停止拨号，查询拨打状态定时任务
 * 			
 * @author: lei.zhang2@100credit.com
 * @time: 2018年5月25日 下午3:44:53
 */
@Component
@Configurable
@EnableScheduling
@EnableAsync
public class IvrSendTask {
	private static String serverIp = null;  

	static {
		// 获取服务器的IP地址，便于后续追踪
		try {
			InetAddress address = InetAddress.getLocalHost();
			serverIp = address.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	/**
	 * @description:Ivr查询拨打状态定时任务 "0 0/3 9-17 * * ?" 
	 * 
	 * 在每天9点到下午17:55期间的每3分钟触发
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年5月25日 下午3:45:52
	 */
	@Async
	@Scheduled(cron = "0 0/3 9-17 * * ?")
	public void taskExecutor(){
	
		
	}
	
}
