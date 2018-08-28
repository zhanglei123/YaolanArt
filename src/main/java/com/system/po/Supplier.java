package com.system.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

//供应商
@TableName("supplier")
public class Supplier extends Model<Supplier>{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@TableField(value="supplier_name")
    private String supplierName;
	
	@TableField(value="supplier_addr")
	private String supplierAddr;

	@TableField(value="linkman_name")
    private String linkmanName;
	
	@TableField(value="linkman_sex")
	private Integer linkmanSex;

	@TableField(value="linkman_phone")
	private String linkmanPhone;
	
	private String remark;
	
	@TableField(value="create_time")
    private Date createTime;

	@TableField(value="update_time")
    private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddr() {
		return supplierAddr;
	}

	public void setSupplierAddr(String supplierAddr) {
		this.supplierAddr = supplierAddr;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public Integer getLinkmanSex() {
		return linkmanSex;
	}

	public void setLinkmanSex(Integer linkmanSex) {
		this.linkmanSex = linkmanSex;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return id;
	}
    
}