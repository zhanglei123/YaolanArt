package com.system.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("student_linkman_info")
public class StudentLinkmanInfo extends Model<StudentLinkmanInfo> {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@TableField(value="student_id")
	private Integer studentId;
	
	@TableField(value="relation_type")
	private Integer relationType;
	
	@TableField(value="linkman_name")
	private String linkmanName;
	
	@TableField(value="linkman_sex")
	private String linkmanSex;

	@TableField(value="linkman_phone")
	private String linkmanPhone;
	
	/**是否是首选联系人 1:是  2:否*/
	@TableField(value="is_first")
	private Integer isFirst;
	
	private String remark;
	
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

	public Integer getRelationType() {
		return relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public Integer getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
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

	public String getLinkmanSex() {
		return linkmanSex;
	}

	public void setLinkmanSex(String linkmanSex) {
		this.linkmanSex = linkmanSex;
	}
	
}