package com.system.util;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.system.base.ResponseCode;
import com.system.po.StudentLesson;
import com.system.po.User;


public class JsonUtil {
	/**
	 * @description:使用fastjson处理
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toResponseMsg(ResponseCode code){
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		return JSON.toJSONString(resMap);
	}
	
	/**
	 * @description:返回一个对象参数
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toResponseObj(ResponseCode code,Object object){
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		resMap.put("object", object);
		return JSON.toJSONString(resMap);
	}
	/**
	 * @description:使用fastjson处理
	 * @author: lei.zhang2@100credit.com
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toResponseMsg(ResponseCode code,String msg){
		Map<String, Object> resMap = new HashMap<String, Object>();
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
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		resMap.put("user", user);
		return JSON.toJSONString(resMap);
	}
	
	
	/**
	 * @description:使用fastjson处理,后台处理返回
	 * 	draw: 表示请求次数
		recordsTotal: 总记录数
		recordsFiltered: 过滤后的总记录数
		data: 具体的数据对象数组(页面可以 【这里对应页面js的  "dataSrc":"list"】设置)
	 * @author: lei.zhang2@100credit.com
	 * @param length 
	 * @param start 
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toDataTableServerMsg(ResponseCode code,Integer draw,Integer totalNum,Integer filteredNum, List<?> list){
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		resMap.put("draw", draw);
		resMap.put("recordsTotal", totalNum);
		resMap.put("recordsFiltered", filteredNum);
		resMap.put("list", list);
		return JSON.toJSONString(resMap);
	}
	
	
	/**
	 * @description:使用fastjson处理,前台处理返回
	 * 	draw: 表示请求次数
		recordsTotal: 总记录数
		recordsFiltered: 过滤后的总记录数
		data: 具体的数据对象数组(页面可以 【这里对应页面js的  "dataSrc":"list"】设置)
	 * @author: lei.zhang2@100credit.com
	 * @param length 
	 * @param start 
	 * @time: 2018年5月24日 下午5:58:48
	 */
	public static String toDataTableFrontMsg(ResponseCode code,List<?> list){
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("code", code.getCode());
		resMap.put("message", code.getMsg());
		resMap.put("list", list);
		return JSON.toJSONString(resMap);
	}


	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		StudentLesson le = new StudentLesson();
		le.setId(1);
		le.setStartTime(new Time(1, 1, 1));
		le.setCreateTime(new Date());
		System.out.println(JSON.toJSONString(le));
	}
}
