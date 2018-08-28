package com.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.system.po.Student;

/**
 * Student学生Service层
 */
public interface StudentService  extends IService<Student>{


    //根据id删除学生信息
    void removeById(Integer id) throws Exception;


    //获取学生总数
    int getCountStudent() throws Exception;


}
