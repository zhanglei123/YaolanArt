package com.system.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * @description:学生消费记录
 * @author: lei.zhang2@100credit.com
 * @time: 2018年9月10日 下午3:33:49
 */
@TableName("student_consumption_record")
public class StudentConsumptionRecord extends Model<StudentConsumptionRecord> {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@TableField(value="student_id")
	private Integer studentId;
	
	@TableField(value="student_name")
	private String studentName;

	/**消费内容类型，1：课程卡次，2：活动*/
	private Integer type;

	@TableField(value="course_id")
	private Integer courseId;
	
	@TableField(value="course_name")
	private String courseName;
	
	@TableField(value="lesson_id")
	private Integer lessonId;
	
	@TableField(value="lesson_name")
	private String lessonName;
	
	private BigDecimal money;
	
	private Integer num;
	
	@TableField(value="course_remain_num")
	private Integer courseRemainNum;
	
	@TableField(value="course_remain_money")
	private BigDecimal courseRemainMoney;
	
	@TableField(value="teacher_id")
	private Integer teacherId;
	
	private String remark;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField(value="create_time")
	private Date createTime;

	@Override
	protected Serializable pkVal() {
		return id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getCourseRemainNum() {
		return courseRemainNum;
	}

	public void setCourseRemainNum(Integer courseRemainNum) {
		this.courseRemainNum = courseRemainNum;
	}

	public BigDecimal getCourseRemainMoney() {
		return courseRemainMoney;
	}

	public void setCourseRemainMoney(BigDecimal courseRemainMoney) {
		this.courseRemainMoney = courseRemainMoney;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	
}