package com.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.system.po.Teacher;

/**
 * Teacher老师Service层
 */
public interface TeacherService extends IService<Teacher>{

    //根据id删除老师信息
    void removeById(Integer id) throws Exception;


    //获取老师总数
    int getCountTeacher() throws Exception;

}
