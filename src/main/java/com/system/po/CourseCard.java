package com.system.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("course_card")
public class CourseCard extends Model<CourseCard>{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Integer type;
	
	private Integer num;
	
	@TableField(value="total_price")
	private BigDecimal totalPrice;
	
	/**平均价格*/
	@TableField(value="average_price")
	private BigDecimal averagePrice;

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



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public Integer getNum() {
		return num;
	}



	public void setNum(Integer num) {
		this.num = num;
	}



	public BigDecimal getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public BigDecimal getAveragePrice() {
		return averagePrice;
	}



	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}



	@Override
	protected Serializable pkVal() {
		return id;
	}
}