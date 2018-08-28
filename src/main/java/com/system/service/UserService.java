package com.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.system.po.User;

public interface UserService extends IService<User>{

    //保存用户登录信息
    void save(User user) throws Exception;

    //根据姓名删除
    void removeByName(String name) throws Exception;

    //根据用户名更新
    void updateByName(String name, User user);

}
