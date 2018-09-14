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
import com.baomidou.mybatisplus.plugins.Page;
import com.system.base.ResponseCode;
import com.system.po.StudentConsumptionRecord;
import com.system.service.StudentConsumptionRecordService;
import com.system.util.JsonUtil;
/**
 * @description:学生刷课记录
 * @author: lei.zhang2@100credit.com
 * @time: 2018年9月10日 下午5:16:57
 */
@Controller
@RequestMapping(value = "/studentConsumptionRecord")
public class StudentConsumptionRecordController {
	//private Logger logger = Logger.getLogger(StudentConsumptionRecordController.class);
    
	@Resource(name = "studentConsumptionRecordServiceImpl")
    private StudentConsumptionRecordService studentConsumptionRecordService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(@RequestParam(value = "draw",required = false,defaultValue = "0") Integer draw, 
    		@RequestParam(value = "start",required = false,defaultValue = "0") Integer start, 
	    	@RequestParam(value = "length",required = false, defaultValue = "10") Integer length,
	    	@RequestParam(value = "student_id",required = false) Integer student_id,
	    	@RequestParam(value = "startTime",required = false) Date startTime,
	    	@RequestParam(value = "endTime",required = false) Date endTime,
	    	HttpServletRequest request) throws Exception {
    	  //总条数
        int totalCount = studentConsumptionRecordService.selectCount(null);
        //查询条件
        int page = (start/length)+1;
        Wrapper<StudentConsumptionRecord> wrapper = new EntityWrapper<StudentConsumptionRecord>().orderBy("id", false);
        if (student_id != null) {
        	wrapper = wrapper.eq("student_id", student_id);
		}
        if (startTime != null) {
        	wrapper = wrapper.gt("create_time", startTime);
		}
        if (endTime != null) {
        	wrapper = wrapper.lt("create_time", endTime);
        }
        //过滤后条数
        int filteredTotalCount = studentConsumptionRecordService.selectCount(wrapper);
        
        Page<StudentConsumptionRecord> selectPage = studentConsumptionRecordService.selectPage(new Page<StudentConsumptionRecord>(page,length), wrapper);
        List<StudentConsumptionRecord> list = selectPage.getRecords();
        return JsonUtil.toDataTableServerMsg(ResponseCode.SUCCESS, draw,totalCount,filteredTotalCount,list);
    }

 
    
    
    @RequestMapping(value = "getRecordById")
    @ResponseBody
    public String getStudentById(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	StudentConsumptionRecord studentConsumptionRecord = studentConsumptionRecordService.selectById(id);
        return JsonUtil.toResponseObj(ResponseCode.SUCCESS,studentConsumptionRecord);
    }
  
}
