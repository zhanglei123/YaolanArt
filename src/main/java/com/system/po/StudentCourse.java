package com.system.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * @description:学生卡次信息，多对多
 * @author: lei.zhang2@100credit.com
 * @time: 2018年9月5日 上午11:33:25
 */
@TableName("student_course")
public class StudentCourse extends Model<StudentCourse> {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@TableField(value="student_id")
	private Integer studentId;
	
	/**类型，1：课程卡次，2：课程活动*/
	private Integer type;
	
	@TableField(value="student_name")
	private String studentName;

	@TableField(value="course_id")
	private Integer courseId;
	
	@TableField(value="course_name")
	private String courseName;
	
	/**卡次或活动初始次数*/
	@TableField(value="original_num")
	private Integer originalNum;
	
	@TableField(value="original_money")
	private BigDecimal originalMoney;
	
	@TableField(value="average_money")
	private BigDecimal averageMoney;
	
	/**卡次或活动剩余次数*/
	@TableField(value="remain_num")
	private Integer remainNum;
	
	@TableField(value="remain_money")
	private BigDecimal remainMoney;
	
	/**状态 1:可用,2:冻结,3:已用尽*/
	private Integer status;
	
	@TableField(value="create_time")
	private Date createTime;
	
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public Integer getOriginalNum() {
		return originalNum;
	}

	public void setOriginalNum(Integer originalNum) {
		this.originalNum = originalNum;
	}

	public BigDecimal getOriginalMoney() {
		return originalMoney;
	}

	public void setOriginalMoney(BigDecimal originalMoney) {
		this.originalMoney = originalMoney;
	}

	public BigDecimal getAverageMoney() {
		return averageMoney;
	}

	public void setAverageMoney(BigDecimal averageMoney) {
		this.averageMoney = averageMoney;
	}

	public Integer getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

	public BigDecimal getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(BigDecimal remainMoney) {
		this.remainMoney = remainMoney;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

}