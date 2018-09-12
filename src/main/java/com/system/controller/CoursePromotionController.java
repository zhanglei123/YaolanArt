package com.system.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.system.base.ResponseCode;
import com.system.po.CoursePromotion;
import com.system.service.CoursePromotionService;
import com.system.util.JsonUtil;

@Controller
@RequestMapping("/coursePromotion")
public class CoursePromotionController {
	@Resource
    private CoursePromotionService coursePromotionService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request) throws Exception {
        Wrapper<CoursePromotion> wrapper = new EntityWrapper<CoursePromotion>().orderBy("id", false);
        List<CoursePromotion> list = coursePromotionService.selectList(wrapper);
        return JsonUtil.toDataTableFrontMsg(ResponseCode.SUCCESS,list);
    }
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(CoursePromotion promotion) throws Exception {
    	//分类名称不能重复
    	int count = coursePromotionService.selectCount(new EntityWrapper<CoursePromotion>().eq("name", promotion.getName().trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"该活动名称已存在");
		}
    	
    	promotion.setCreateTime(new Date());
    	coursePromotionService.insert(promotion);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(CoursePromotion type) throws Exception {
    	//分类名称不能重复(不包含自己)
    	int count = coursePromotionService.selectCount(new EntityWrapper<CoursePromotion>()
    			.notIn("id", type.getId())
    			.eq("name", type.getName().trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"该活动名称已存在");
		}
    	coursePromotionService.insertOrUpdate(type);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
  
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	coursePromotionService.deleteById(id);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
}
