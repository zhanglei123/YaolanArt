package com.system.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.po.Config;
import com.system.service.ConfigService;
import com.system.util.IpUtil;
import com.system.util.MailUtil;
/**
 * @description:联通拨号网络，每次重启路由，ip重新分配
 * 				该定时任务，每天获取ip，当ip变动发送邮件提醒
 * @author: lei.zhang2@100credit.com
 * @time: 2018年9月10日 下午6:56:38
 */
@Component
@EnableScheduling
@EnableAsync
public class IpCheckTask {
	private static Logger logger = Logger.getLogger(IpCheckTask.class);
	@Resource
    private ConfigService configService;
	
	
	@Async
	@Scheduled(cron = "0 0 1 * * ?")
	public void taskExecutor(){
		logger.info("检查公网IP定时任务启动");
		String networkIP = IpUtil.getPublicNetworkIP();
		//获取数据库的ip
		Config config = configService.selectOne(new EntityWrapper<Config>()
				.eq("config_name", "network_ip"));
		if (config == null) {
			//发送邮件提醒
			MailUtil.alarm("数据库配置错误", "数据库未配置network_ip信息");
			logger.error("数据库未配置network_ip信息");
		}else {
			//判断是否相同
			if (!networkIP.equals(config.getConfigValue())) {
				//发送邮件提醒
				MailUtil.alarm("公网ip变更", "变更前ip为："+config.getConfigValue()+"变更后为："+networkIP);
				//更新数据
				config.setConfigValue(networkIP);
				configService.updateById(config);
			}
		}
	}
	
}
