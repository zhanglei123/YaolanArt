package com.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.mapper.GoodsTypeMapper;
import com.system.po.GoodsType;
import com.system.service.GoodsTypeService;

@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements GoodsTypeService {

	@Autowired(required = false)
    private GoodsTypeMapper goodsTypeMapper;



    


}
