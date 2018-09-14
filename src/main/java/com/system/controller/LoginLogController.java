package com.system.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.system.base.ResponseCode;
import com.system.po.LoginLog;
import com.system.po.User;
import com.system.service.LoginLogService;
import com.system.service.UserService;
import com.system.util.JsonUtil;

@Controller
@RequestMapping("/loginLog")
public class LoginLogController {
	@Resource
    private UserService userService;
	@Resource
	private LoginLogService loginLogService;

    // 查询当前登录用户的登录记录信息
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(@RequestParam(value = "draw",required = false,defaultValue = "0") Integer draw, 
    		@RequestParam(value = "start",required = false,defaultValue = "0") Integer start, 
	    	@RequestParam(value = "length",required = false, defaultValue = "10") Integer length,
	    	HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        User currentUser = userService.selectOne(new EntityWrapper<User>()
				.eq("user_name", username));
        
        //总条数
        int totalCount = loginLogService.selectCount(new EntityWrapper<LoginLog>()
				.eq("user_id", currentUser.getId()));
        //分页条数
        int page = start/length;
        Page<LoginLog> selectPage = loginLogService.selectPage(new Page<LoginLog>(page+1,length), new EntityWrapper<LoginLog>()
				.eq("user_id", currentUser.getId())
				.orderBy("id", false));
        List<LoginLog> list = selectPage.getRecords();
        return JsonUtil.toDataTableServerMsg(ResponseCode.SUCCESS, draw,totalCount,totalCount,list);
    }
}
