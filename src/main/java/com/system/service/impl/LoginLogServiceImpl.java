package com.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.mapper.LoginLogMapper;
import com.system.po.LoginLog;
import com.system.service.LoginLogService;

@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
	@Autowired(required = false)
    private LoginLogMapper loginLogMapper;




}
