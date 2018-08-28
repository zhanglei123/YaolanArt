package com.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.system.po.Course;

/**
 * CourseService课程信息.
 */
public interface CourseService extends IService<Course>{

    //根据id删除课程信息
    Boolean removeById(Integer id) throws Exception;

    //获取课程总数
    int getCountCouse() throws Exception;


}
