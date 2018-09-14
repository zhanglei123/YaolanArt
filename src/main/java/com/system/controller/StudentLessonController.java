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
import com.baomidou.mybatisplus.plugins.Page;
import com.system.base.ResponseCode;
import com.system.po.Lesson;
import com.system.po.Student;
import com.system.po.StudentLesson;
import com.system.service.LessonService;
import com.system.service.StudentCourseService;
import com.system.service.StudentLessonService;
import com.system.service.StudentRechargeRecordService;
import com.system.service.StudentService;
import com.system.util.JsonUtil;
/**
 * @description:学生课程信息
 * @author: lei.zhang2@100credit.com
 * @time: 2018年9月10日 下午3:29:52
 */
@Controller
@RequestMapping(value = "/studentLesson")
public class StudentLessonController {
	//private Logger logger = Logger.getLogger(StudentLessonController.class);
  
	@Resource
    private StudentLessonService studentLessonService;
	@Resource
	private StudentService studentService;
	@Resource
	private LessonService lessonService;
	@Resource
    private StudentCourseService studentCourseService;
	@Resource
	private StudentRechargeRecordService studentRechargeRecordService;

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
	        int totalCount = studentLessonService.selectCount(null);
	        //过滤后条数
	        int page = (start/length)+1;
	        Wrapper<StudentLesson> wrapper = new EntityWrapper<StudentLesson>().orderBy("id", false);
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
	        int filteredTotalCount = studentLessonService.selectCount(wrapper);
	        
	        Page<StudentLesson> selectPage = studentLessonService.selectPage(new Page<StudentLesson>(page,length), wrapper);
	        List<StudentLesson> list = selectPage.getRecords();
	        return JsonUtil.toDataTableServerMsg(ResponseCode.SUCCESS, draw,totalCount,filteredTotalCount,list);
    }
    
    /**
     * @description:在学生的卡次管理页面新增课程
     * @author: lei.zhang2@100credit.com
     * @time: 2018年9月6日 下午2:13:00
     * @param studentLesson 只接收到 学生id 课程id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addByStudent")
    @ResponseBody
    public String addByStudent(@ModelAttribute StudentLesson studentLesson) throws Exception {
    	Lesson lesson = lessonService.selectById(studentLesson.getLessonId());
    	Student student = studentService.selectById(studentLesson.getStudentId());
    	if (lesson == null || student == null) {
    		return JsonUtil.toResponseObj(ResponseCode.FAIL,"学生或课程信息为空");
		}
    	studentLesson.setLessonName(lesson.getName());
    	studentLesson.setCreateTime(new Date());
    	studentLesson.setUpdateTime(new Date());
    	studentLessonService.insert(studentLesson);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(@ModelAttribute StudentLesson studentLesson,
    		HttpServletRequest request) throws Exception {
    	studentLesson.setCreateTime(new Date());
    	studentLesson.setUpdateTime(new Date());
    	studentLessonService.insert(studentLesson);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(StudentLesson studentLesson) throws Exception {
    	studentLesson.setUpdateTime(new Date());
    	studentLessonService.insertOrUpdate(studentLesson);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	studentLessonService.deleteById(id);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }

    
    /**
     * @description:获取当前可用的课程
     * 				
     * @author: lei.zhang2@100credit.com
     * @time: 2018年9月7日 下午5:21:48
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAvailableLesson")
    @ResponseBody
    public String getAvailableLesson(@RequestParam(value = "studentId",required = true) Integer studentId) throws Exception {
    	List<StudentLesson> list = studentLessonService.selectList(new EntityWrapper<StudentLesson>()
					.eq("student_id", studentId)
					.orderBy("id", true));
    	return JsonUtil.toDataTableFrontMsg(ResponseCode.SUCCESS,list);
    }
    
    
    
}
