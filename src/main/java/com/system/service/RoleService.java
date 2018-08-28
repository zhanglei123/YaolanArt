package com.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.system.po.Role;

/**
 *  Role 权限表Service层
 */
public interface RoleService  extends IService<Role>{

    Role findByid(Integer id) throws Exception;

}
