package com.system.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.system.base.ResponseCode;
import com.system.po.Lesson;
import com.system.service.LessonService;
import com.system.util.JsonUtil;

@Controller
@RequestMapping("/lesson")
public class LessonController {
	@Resource
    private LessonService lessonService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(@RequestParam(value = "type_s",required = false) Integer type,
    		HttpServletRequest request) throws Exception {
        Wrapper<Lesson> wrapper = new EntityWrapper<Lesson>().orderBy("id", false);
        if (type != null) {
        	wrapper = wrapper.eq("type", type);
		}
        List<Lesson> list = lessonService.selectList(wrapper);
        return JsonUtil.toDataTableFrontMsg(ResponseCode.SUCCESS,list);
    }
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(@ModelAttribute Lesson lesson) throws Exception {
    	//分类名称不能重复
    	int count = lessonService.selectCount(new EntityWrapper<Lesson>().eq("name", lesson.getName().trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"该课程名称已存在");
		}
    	lesson.setCreateTime(new Date());
    	lesson.setUpdateTime(new Date());
    	lessonService.insert(lesson);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Lesson lesson) throws Exception {
    	//分类名称不能重复(不包含自己)
    	int count = lessonService.selectCount(new EntityWrapper<Lesson>()
    			.notIn("id", lesson.getId())
    			.eq("name", lesson.getName().trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"该课程名称已存在");
		}
    	lesson.setUpdateTime(new Date());
    	lessonService.insertOrUpdate(lesson);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	lessonService.deleteById(id);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
   
}
