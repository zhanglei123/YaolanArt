package com.system.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class IpUtil {
	private static Logger logger = Logger.getLogger(IpUtil.class);
	
	/**获取ip的url*/
	private static String IP_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=myip";
	
	
	/**
	 * @description:获取公网IP
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年9月10日 下午6:51:16
	 * @return
	 */
	public static String getPublicNetworkIP(){
		String ip = "";
		String resStr = HttpUtil.sendGet(IP_URL);
		logger.info("获取公网IP,返回报文："+resStr);
		if (!StringUtils.isEmpty(resStr)) {
			// 解析
			JSONObject resJson = JSONObject.fromObject(resStr);
			if (resJson.containsKey("code")) {
				int code = resJson.getInt("code");
				if (0 == code) {
					//解析
					JSONObject dataObj = resJson.getJSONObject("data");
					ip = dataObj.getString("ip");
				} else {
					logger.error("第三方返回失败");
				}
			} else {
				logger.error("第三方返回失败，无code");
			}
		} else {
			logger.error("第三方返回为空");
		}
		return ip;
	}

	public static void main(String[] args) {
		String networkIP = getPublicNetworkIP();
		System.out.println(networkIP);
	}
}
