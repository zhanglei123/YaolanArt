package com.system.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.base.ResponseCode;
import com.system.po.User;
import com.system.service.UserService;
import com.system.util.JsonUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
    private UserService userService;

    // 本账户密码重置
    @RequestMapping(value = "/updatePwd", method = {RequestMethod.POST})
    @ResponseBody
    public String updatePwd(@RequestParam(value = "oldPassword",required = true)String oldPassword, 
    		@RequestParam(value = "newPassword",required = true)String newPassword) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        User currentUser = userService.selectOne(new EntityWrapper<User>()
				.eq("user_name", username));

        if (!oldPassword.equals(currentUser.getPassword())) {
        	return JsonUtil.toResponseMsg(ResponseCode.FAIL,"旧密码错误");
        } else {
        	currentUser.setPassword(newPassword);
            userService.insertOrUpdate(currentUser);
        }
        subject.logout();
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    
    // 本账户密码重置
    @RequestMapping(value = "/getCurrentUser", method = {RequestMethod.POST})
    @ResponseBody
    public String getCurrentUser() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        User currentUser = userService.selectOne(new EntityWrapper<User>()
				.eq("user_name", username));
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS,currentUser);
    }
    
    // 用户信息修改
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public String update(User user) throws Exception {
    	Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        User currentUser = userService.selectOne(new EntityWrapper<User>()
				.eq("user_name", username));
        //赋值
        user.setId(currentUser.getId());
        user.setUpdateTime(new Date());
        userService.insertOrUpdate(user);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS,currentUser);
    }
}
