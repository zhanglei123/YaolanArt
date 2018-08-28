package com.system.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


public class CommonUtil {
	/**
	 * @description:获取服务器机器ip
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年8月23日 下午2:51:34
	 * @return
	 */
	public static String getIp(){
		String serverIp = "0";
		// 获取服务器的IP地址，便于后续追踪
		try {
			InetAddress address = InetAddress.getLocalHost();
			serverIp = address.getHostAddress();
			return serverIp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serverIp;
	}

	/**
	 * @description:获取客户端请求ip
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年8月23日 下午2:53:59
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

}
