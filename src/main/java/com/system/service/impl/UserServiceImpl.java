package com.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.mapper.UserMapper;
import com.system.po.User;
import com.system.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired(required = false)
    private UserMapper userMapper;



    public void save(User user) throws Exception {
        userMapper.insert(user);
    }



	@Override
	public void removeByName(String name) throws Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updateByName(String name, User user) {
		// TODO Auto-generated method stub
		
	}



}
