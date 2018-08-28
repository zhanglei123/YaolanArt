package com.system.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.system.base.ResponseCode;
import com.system.po.User;


public class JsonUtil {
	public static final Map<String, Object> resMap = new HashMap<String, Object>();
	/**
	 * @description:使用fastjson处理
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toResponseMsg(ResponseCode code){
		resMap.clear();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		return JSON.toJSONString(resMap);
	}
	
	/**
	 * @description:使用fastjson处理
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toResponseMsg(ResponseCode code,String msg){
		resMap.clear();
		resMap.put("code", code.getCode());
		resMap.put("message", msg);
		return JSON.toJSONString(resMap);
	}
	
	/**
	 * @description:使用fastjson处理
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toResponseMsg(ResponseCode code,User user){
		resMap.clear();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		resMap.put("user", user);
		return JSON.toJSONString(resMap);
	}
	
	/**
	 * @description:使用fastjson处理
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toResponseMsg(ResponseCode code,List<?> list){
		resMap.clear();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		resMap.put("list", list);
		return JSON.toJSONString(resMap);
	}
	
	/**
	 * @description:使用fastjson处理
	 * 	draw: 表示请求次数
		recordsTotal: 总记录数
		recordsFiltered: 过滤后的总记录数
		data: 具体的数据对象数组(页面可以 【这里对应页面js的  "dataSrc":"list"】设置)
	 * @author: lei.zhang2@100credit.com
	 * @param length 
	 * @param start 
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toDataTableMsg(ResponseCode code,Integer draw,Integer totalNum,Integer filteredNum, List<?> list){
		resMap.clear();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		resMap.put("draw", draw);
		resMap.put("recordsTotal", totalNum);
		resMap.put("recordsFiltered", filteredNum);
		resMap.put("list", list);
		return JSON.toJSONString(resMap);
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
		String sd = sdf.format(new Date(1535004251000l));   // 时间戳转换成时间
		        System.out.println(sd);//打印出你要的时间

	}
}
