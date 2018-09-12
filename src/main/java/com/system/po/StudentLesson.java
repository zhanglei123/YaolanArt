package com.system.po;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * @description:学生课程信息
 * @author: lei.zhang2@100credit.com
 * @time: 2018年9月5日 上午11:33:25
 */
@TableName("student_lesson")
public class StudentLesson extends Model<StudentLesson> {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@TableField(value="student_id")
	private Integer studentId;
	
	@TableField(value="lesson_id")
	private Integer lessonId;
	
	@TableField(value="lesson_name")
	private String lessonName;
	
	/**刷课使用卡次次数*/
	@TableField(value="use_course_num")
	private Integer useCourseNum;
	
	/**周几上课，1,3 代表周一，周三上课*/
	@TableField(value="week")
	private Integer week;
	
	@JSONField(format="HH:mm:ss")
	@TableField(value="start_time")
	private Time startTime;
	
	@JSONField(format="HH:mm:ss")
	@TableField(value="end_time")
	private Time endTime;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField(value="create_time")
	private Date createTime;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@TableField(value="update_time")
	private Date updateTime;

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

	public Integer getUseCourseNum() {
		return useCourseNum;
	}

	public void setUseCourseNum(Integer useCourseNum) {
		this.useCourseNum = useCourseNum;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

}