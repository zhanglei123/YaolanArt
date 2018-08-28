package com.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.system.po.College;

public interface CollegeService extends IService<College>{

    List<College> finAll() throws Exception;

}
