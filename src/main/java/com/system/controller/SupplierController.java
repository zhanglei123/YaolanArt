package com.system.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.system.base.ResponseCode;
import com.system.po.Supplier;
import com.system.service.SupplierService;
import com.system.util.JsonUtil;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Resource
    private SupplierService supplierService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(@RequestParam(value = "draw",required = false,defaultValue = "0") Integer draw, 
    		@RequestParam(value = "start",required = false,defaultValue = "0") Integer start, 
	    	@RequestParam(value = "length",required = false, defaultValue = "10") Integer length,
	    	@RequestParam(value = "supplier_name_s",required = false) String supplier_name_s,
	    	@RequestParam(value = "startTime",required = false) Date startTime,
	    	@RequestParam(value = "endTime",required = false) Date endTime,
	    	HttpServletRequest request) throws Exception {
    	  //总条数
        int totalCount = supplierService.selectCount(null);
        //过滤后条数
        int page = (start/length)+1;
        Wrapper<Supplier> wrapper = new EntityWrapper<Supplier>().orderBy("id", false);
        if (!StringUtils.isEmpty(supplier_name_s)) {
        	wrapper = wrapper.like("supplier_name", "%"+supplier_name_s+"%");
		}
        if (startTime != null) {
        	wrapper = wrapper.gt("create_time", startTime);
		}
        if (endTime != null) {
        	wrapper = wrapper.lt("create_time", endTime);
        }
        
        Page<Supplier> selectPage = supplierService.selectPage(new Page<Supplier>(page,length), wrapper);
        List<Supplier> list = selectPage.getRecords();
        return JsonUtil.toDataTableServerMsg(ResponseCode.SUCCESS, draw,totalCount,totalCount,list);
    }
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(Supplier supplier) throws Exception {
    	//分类名称不能重复
    	int count = supplierService.selectCount(new EntityWrapper<Supplier>().eq("supplier_name", supplier.getSupplierName()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"改供应商名称已存在");
		}
    	supplier.setCreateTime(new Date());
    	supplier.setUpdateTime(new Date());
    	supplierService.insert(supplier);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Supplier supplier) throws Exception {
    	//分类名称不能重复(不包含自己)
    	int count = supplierService.selectCount(new EntityWrapper<Supplier>()
    			.notIn("id", supplier.getId())
    			.eq("supplier_name", supplier.getSupplierName().trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"改供应商名称已存在");
		}
    	supplier.setUpdateTime(new Date());
    	supplierService.insertOrUpdate(supplier);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    /**
     * @description:删除供应商，需要判断分类下的物品数量
     * @author: lei.zhang2@100credit.com
     * @time: 2018年8月24日 下午4:09:02
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id",required = true) Integer id) throws Exception {
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
}
