package com.system.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("goods")
public class Goods extends Model<Goods>{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@TableField(value="type_id")
	private Integer typeId;
	
	@TableField(exist = false)
	private String typeName;

    private String name;
    
    private String version;
    
    private String colour;
    
    private Integer num;
    
    private String unit;
    
    @TableField(value="image_path")
    private String imagePath;

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



	public Integer getTypeId() {
		return typeId;
	}



	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public String getColour() {
		return colour;
	}



	public void setColour(String colour) {
		this.colour = colour;
	}



	public Integer getNum() {
		return num;
	}



	public void setNum(Integer num) {
		this.num = num;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	@Override
	protected Serializable pkVal() {
		return id;
	}
}