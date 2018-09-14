package com.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.mapper.RoleMapper;
import com.system.po.Role;
import com.system.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


	@Override
	public Role findByid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
