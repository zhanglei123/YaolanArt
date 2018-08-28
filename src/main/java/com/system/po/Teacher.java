package com.system.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("teacher")
public class Teacher extends Model<CourseCard> {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Integer sex;

	private String phone;
	
	@TableField(value="id_card")
	private String idCard;

	private String email;
	
	private String qq;
	
	@TableField(value="image_path")
	private String imagePath;
	
	@TableField(value="course_type")
	private Integer courseType;
	
	@TableField(value="education_school")
	private String educationSchool;
	
	@TableField(value="education_type")
	private Integer educationType;
	
	@TableField(value="education_year")
	private Integer educationYear;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public String getEducationSchool() {
		return educationSchool;
	}

	public void setEducationSchool(String educationSchool) {
		this.educationSchool = educationSchool;
	}

	public Integer getEducationType() {
		return educationType;
	}

	public void setEducationType(Integer educationType) {
		this.educationType = educationType;
	}

	public Integer getEducationYear() {
		return educationYear;
	}

	public void setEducationYear(Integer educationYear) {
		this.educationYear = educationYear;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
}