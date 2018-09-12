package com.system.controller;

import java.math.BigDecimal;
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
import com.baomidou.mybatisplus.plugins.Page;
import com.system.base.ResponseCode;
import com.system.po.CourseCard;
import com.system.po.Student;
import com.system.po.StudentConsumptionRecord;
import com.system.po.StudentCourse;
import com.system.po.StudentLesson;
import com.system.po.StudentRechargeRecord;
import com.system.service.CourseCardService;
import com.system.service.StudentConsumptionRecordService;
import com.system.service.StudentCourseService;
import com.system.service.StudentLessonService;
import com.system.service.StudentRechargeRecordService;
import com.system.service.StudentService;
import com.system.util.JsonUtil;
/**
 * @description:学生的卡次记录信息
 * @author: lei.zhang2@100credit.com
 * @time: 2018年9月10日 下午4:23:53
 */
@Controller
@RequestMapping(value = "/studentCourse")
public class StudentCourseController {
	private Logger logger = Logger.getLogger(StudentCourseController.class);
  
	@Resource
    private StudentCourseService studentCourseService;
	@Resource
	private StudentService studentService;
	@Resource
	private CourseCardService courseCardService;
	@Resource
	private StudentLessonService studentLessonService;
	@Resource
	private StudentRechargeRecordService studentRechargeRecordService;
	@Resource
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
        int totalCount = studentCourseService.selectCount(null);
        //过滤后条数
        int page = (start/length)+1;
        Wrapper<StudentCourse> wrapper = new EntityWrapper<StudentCourse>().orderBy("id", false);
        if (student_id != null) {
        	wrapper = wrapper.eq("student_id", student_id);
		}
        if (startTime != null) {
        	wrapper = wrapper.gt("create_time", startTime);
		}
        if (endTime != null) {
        	wrapper = wrapper.lt("create_time", endTime);
        }
       
        
        Page<StudentCourse> selectPage = studentCourseService.selectPage(new Page<StudentCourse>(page,length), wrapper);
        List<StudentCourse> list = selectPage.getRecords();
        return JsonUtil.toDataTableServerMsg(ResponseCode.SUCCESS, draw,totalCount,totalCount,list);
    }
    
    @RequestMapping(value = "getStudentCourseById")
    @ResponseBody
    public String getStudentCourseById(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	StudentCourse studentCourse = studentCourseService.selectById(id);
        return JsonUtil.toResponseObj(ResponseCode.SUCCESS,studentCourse);
    }
    
    /**
     * @description:在学生的卡次管理页面新增卡次
     * @author: lei.zhang2@100credit.com
     * @time: 2018年9月6日 下午2:13:00
     * @param studentCourse 只接收到 学生id 卡次id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addByStudent")
    @ResponseBody
    public String addByStudent(@ModelAttribute StudentCourse studentCourse) throws Exception {
    	CourseCard courseCard = courseCardService.selectById(studentCourse.getCourseId());
    	Student student = studentService.selectById(studentCourse.getStudentId());
    	if (courseCard == null || student == null) {
    		return JsonUtil.toResponseObj(ResponseCode.FAIL,"学生或卡次信息为空");
		}
    	//先只绑定课程卡次（活动先忽略）
    	studentCourse.setType(1);
    	//该卡次的状态，如果已经有可用的卡次，该卡次冻结
    	List<StudentCourse> courseList = studentCourseService.selectList(new EntityWrapper<StudentCourse>()
    									.eq("student_id", student.getId())
    									.eq("type", 1)
    									.eq("status", 1));
    	if (courseList!=null && courseList.size() > 0) {
    		studentCourse.setStatus(2);
		}else {
			studentCourse.setStatus(1);
		}
    	
    	studentCourse.setStudentName(student.getName());
    	studentCourse.setCourseName(courseCard.getName());
    	studentCourse.setOriginalNum(courseCard.getNum());//初始次数
    	studentCourse.setOriginalMoney(courseCard.getTotalPrice());
    	studentCourse.setAverageMoney(courseCard.getAveragePrice());
    	studentCourse.setRemainMoney(courseCard.getTotalPrice());
    	studentCourse.setRemainNum(courseCard.getNum());
    	studentCourse.setCreateTime(new Date());
    	studentCourse.setUpdateTime(new Date());
    	studentCourseService.insert(studentCourse);
    	
    	logger.info("添加一条消费记录");
    	//添加一条消费记录
    	StudentRechargeRecord record = new StudentRechargeRecord();
    	record.setStudentId(student.getId());
    	record.setStudentName(student.getName());
    	record.setType(1);//先只有课程卡次
    	record.setCourseId(courseCard.getId());
    	record.setCourseName(courseCard.getName());
    	// TODO 价格
    	record.setMoney(courseCard.getTotalPrice());
    	// TODO 付款方式
    	record.setRechargeType(1);
    	studentRechargeRecordService.insert(record);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(@ModelAttribute StudentCourse studentCourse,
    		HttpServletRequest request) throws Exception {
    	studentCourse.setCreateTime(new Date());
    	studentCourse.setUpdateTime(new Date());
    	studentCourseService.insert(studentCourse);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(StudentCourse studentCourse) throws Exception {
    	studentCourse.setUpdateTime(new Date());
    	studentCourseService.insertOrUpdate(studentCourse);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	studentCourseService.deleteById(id);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    /**
     * @description:获取当前可用的卡次信息，只能有一个可用
     * 				
     * @author: lei.zhang2@100credit.com
     * @time: 2018年9月7日 下午5:21:48
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAvailableCourse")
    @ResponseBody
    public String getAvailableCourse(@RequestParam(value = "studentId",required = true) Integer studentId) throws Exception {
    	StudentCourse studentCourse = studentCourseService.getAvailableCourse(studentId);
    	if (studentCourse == null) {
    		return JsonUtil.toResponseObj(ResponseCode.FAIL,"该学生课次已用尽！");
		}else{
			return JsonUtil.toResponseObj(ResponseCode.SUCCESS,studentCourse);
		}
    }

    /**
     * @description:刷课
     * @author: lei.zhang2@100credit.com
     * @time: 2018年9月10日 下午3:54:01
     * @param studentId 学生ID
     * @param studentId 消费课程ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/studentCouseUse")
    @ResponseBody
    public String studentCouseUse(@RequestParam(value = "studentId",required = true) Integer studentId,
    		@RequestParam(value = "strdentLessonId",required = true) Integer strdentLessonId) throws Exception {
    	StudentLesson studentLesson = studentLessonService.selectById(strdentLessonId);
    	Student student = studentService.selectById(studentId);
    	if (studentLesson == null || student == null) {
    		return JsonUtil.toResponseObj(ResponseCode.FAIL,"学生或学生课程信息为空");
		}
    	//获取可用的学生卡次
    	StudentCourse studentCourse = studentCourseService.getAvailableCourse(studentId);
    	if (studentCourse == null) {
    		return JsonUtil.toResponseObj(ResponseCode.FAIL,"该学生课次已用尽！");
		}
    	//更新卡次的剩余次数(剩余次数 - 课程每次消耗次数)
    	studentCourse.setRemainNum(studentCourse.getRemainNum()-studentLesson.getUseCourseNum());
    	//更新卡次的剩余金额(剩余金额 - (平均金额 * 课程每次消耗次数))
    	BigDecimal useMoney = studentCourse.getAverageMoney().multiply(new BigDecimal(studentLesson.getUseCourseNum()));
    	BigDecimal reMoney = studentCourse.getRemainMoney().subtract(studentCourse.getAverageMoney().multiply(new BigDecimal(studentLesson.getUseCourseNum())));
    	logger.info(student.getName()+"此次刷课金额为："+reMoney.toString());
    	studentCourse.setRemainMoney(reMoney);
    	studentCourse.setUpdateTime(new Date());
    	studentCourseService.updateById(studentCourse);
    	
    	//新增一条消费记录
    	logger.info("新增一条消费记录");
    	StudentConsumptionRecord record =new StudentConsumptionRecord();
    	record.setStudentId(studentId);
    	record.setStudentName(student.getName());
    	// TODO 消费内容类型，1：课程卡次，2：活动
    	record.setType(1);
    	record.setCourseId(studentCourse.getCourseId());
    	record.setCourseName(studentCourse.getCourseName());
    	record.setLessonId(studentLesson.getLessonId());
    	record.setLessonName(studentLesson.getLessonName());
    	//消费金额
    	record.setMoney(useMoney);
    	record.setNum(studentLesson.getUseCourseNum());
    	record.setCourseRemainNum(studentCourse.getRemainNum());
    	record.setCourseRemainMoney(studentCourse.getRemainMoney());
    	record.setTeacherId(1);
    	record.setCreateTime(new Date());
    	studentConsumptionRecordService.insert(record);
    	return JsonUtil.toResponseObj(ResponseCode.SUCCESS,record);
    }
}
