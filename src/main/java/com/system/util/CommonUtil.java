package com.system.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
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
	 * @description:通过base64编码获取文件格式
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年8月23日 下午2:53:59
	 * @param request
	 * @return
	 */
	public static String getBASE64FileSuffix(String base64Str) {
		String str1 = base64Str.split("base64")[0];
		String str2 = str1.substring(str1.indexOf("image")+6, str1.indexOf(";"));
		if ("jpeg".equals(str2)) {
			return ".jpg";
		}
        return "."+str2;
    }
	
	
	/**
	 * @description:解析base64编码并保存文件
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年8月23日 下午2:53:59
	 * @param request
	 * @return
	 */

	public static void saveBASE64File(String base64Str,String filePath) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();  
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(base64Str);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成本地录音文件
	        OutputStream outs = new FileOutputStream(filePath);  
	        outs.write(bytes);  
	        outs.flush();  
	        outs.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * @description:获取客户端请求ip（内网）
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年9月12日 下午2:51:44
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

	
	public static void main(String[] args) {
		String base64= "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wB";
		System.out.println(getBASE64FileSuffix(base64));
	}
}
