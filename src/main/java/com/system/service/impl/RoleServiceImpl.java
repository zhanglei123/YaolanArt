package com.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.mapper.RoleMapper;
import com.system.mapper.StudentMapper;
import com.system.po.Role;
import com.system.po.Student;
import com.system.service.RoleService;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

	@Override
	public Role findByid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
