package com.system.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.system.base.ResponseCode;
import com.system.po.LoginLog;
import com.system.po.User;
import com.system.service.LoginLogService;
import com.system.service.UserService;
import com.system.util.CommonUtil;
import com.system.util.JsonUtil;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private LoginLogService loginLogService;
	
    //登录表单处理
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public String login(User user,HttpServletRequest request) throws Exception {
        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();

        LoginLog log= new LoginLog();
        log.setUserName(user.getUserName());
        log.setIp(CommonUtil.getClientIp(request));
        log.setCreateTime(new Date());
        
        User currentUser = userService.selectOne(new EntityWrapper<User>()
				.eq("user_name", user.getUserName()));
        if (currentUser!=null) {
        	log.setUserId(currentUser.getId());
		}
        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        boolean isSuc = true;
		try {
			subject.login(token);
		} catch ( UnknownAccountException uae ) {
			isSuc = false;
			return JsonUtil.toResponseMsg(ResponseCode.LOGIN_ACCOUNT_ERROR);
		} catch ( IncorrectCredentialsException ice ) { 
			isSuc = false;
			return JsonUtil.toResponseMsg(ResponseCode.LOGIN_PWD_ERROR);
		} catch ( LockedAccountException lae ) {
			isSuc = false;
			return JsonUtil.toResponseMsg(ResponseCode.LOGIN_ACCOUNT_LOCKED);
		} catch ( ExcessiveAttemptsException eae ) {
			isSuc = false;
			return JsonUtil.toResponseMsg(ResponseCode.LOGIN_EXCESSIVE_ATTEMPTS);
		} catch ( AuthenticationException ae) {
			isSuc = false;
			return JsonUtil.toResponseMsg(ResponseCode.LOGIN_AUTHENTICATION_FAIL);
		}finally{
			if (isSuc) {
				log.setState(1);
			}else {
				log.setState(2);
			}
			loginLogService.insert(log);
		}

		
		//设置超时时间
		SecurityUtils.getSubject().getSession().setTimeout(1800000);
        if (subject.hasRole("admin")) {
            return JsonUtil.toResponseMsg(ResponseCode.SUCCESS,currentUser);
        } else if (subject.hasRole("teacher")) {
            return "redirect:/teacher/showCourse";
        } else if (subject.hasRole("student")) {
            return "redirect:/student/showCourse";
        }
        return JsonUtil.toResponseMsg(ResponseCode.LOGIN_FAIL);
    }


}
