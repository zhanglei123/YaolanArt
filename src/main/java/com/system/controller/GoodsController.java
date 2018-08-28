package com.system.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.system.base.ResponseCode;
import com.system.po.Goods;
import com.system.service.GoodsService;
import com.system.util.JsonUtil;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource
    private GoodsService goodsService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(@RequestParam(value = "draw",required = false,defaultValue = "0") Integer draw, 
    		@RequestParam(value = "start",required = false,defaultValue = "0") Integer start, 
	    	@RequestParam(value = "length",required = false, defaultValue = "10") Integer length,
	    	HttpServletRequest request) throws Exception {
        //总条数
        int totalCount = goodsService.selectCount(null);
        //过滤后条数
        int page = (start/length)+1;
        Wrapper<Goods> wrapper = new EntityWrapper<Goods>().orderBy("id", false);
        Page<Goods> selectPage = goodsService.selectPage(new Page<Goods>(page,length), wrapper);
        List<Goods> list = selectPage.getRecords();
        return JsonUtil.toDataTableServerMsg(ResponseCode.SUCCESS, draw,totalCount,totalCount,list);
    }
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(@RequestParam(value = "name",required = true) String name,
    		@RequestParam(value = "name",required = true) String remark) throws Exception {
    	//分类名称不能重复
    	int count = goodsService.selectCount(new EntityWrapper<Goods>().eq("name", name.trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"改分类名称已存在");
		}
    	
    	Goods type = new Goods();
    	type.setName(name.trim());
    	type.setRemark(remark.trim());
    	type.setCreateTime(new Date());
    	goodsService.insert(type);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Goods type) throws Exception {
    	//分类名称不能重复(不包含自己)
    	int count = goodsService.selectCount(new EntityWrapper<Goods>()
    			.notIn("id", type.getId())
    			.eq("name", type.getName().trim()));
    	if (count >0) {
    		return JsonUtil.toResponseMsg(ResponseCode.FAIL,"改分类名称已存在");
		}
    	goodsService.insertOrUpdate(type);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    /**
     * @description:删除分类，需要判断分类下的物品数量
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
