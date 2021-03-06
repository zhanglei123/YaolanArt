package com.system.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.system.base.ResponseCode;
import com.system.po.CourseCard;
import com.system.service.CourseCardService;
import com.system.util.JsonUtil;

@Controller
@RequestMapping("/courseCard")
public class CourseCardController {
	private Logger logger = Logger.getLogger(StudentController.class);
	@Resource
    private CourseCardService courseCardService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request) throws Exception {
    	logger.info("=============开始查询课程卡次列表=============");
        Wrapper<CourseCard> wrapper = new EntityWrapper<CourseCard>().orderBy("id", false);
        List<CourseCard> list = courseCardService.selectList(wrapper);
        return JsonUtil.toDataTableFrontMsg(ResponseCode.SUCCESS,list);
    }
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(@ModelAttribute CourseCard card) throws Exception {
    	//分类名称不能重复
    	int count = courseCardService.selectCount(new EntityWrapper<CourseCard>().eq("name", card.getName().trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"该课程卡次名称已存在");
		}
    	//计算平均价格
    	card.setAveragePrice(card.getTotalPrice().divide(new BigDecimal(card.getNum()),0,RoundingMode.CEILING));
    	card.setCreateTime(new Date());
    	courseCardService.insert(card);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(CourseCard card) throws Exception {
    	//分类名称不能重复(不包含自己)
    	int count = courseCardService.selectCount(new EntityWrapper<CourseCard>()
    			.notIn("id", card.getId())
    			.eq("name", card.getName().trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"该课程卡次名称已存在");
		}
    	card.setUpdateTime(new Date());
    	courseCardService.insertOrUpdate(card);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	courseCardService.deleteById(id);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
   
}
