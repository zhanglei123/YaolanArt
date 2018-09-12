package com.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.system.po.StudentCourse;

public interface StudentCourseService  extends IService<StudentCourse>{

	StudentCourse getAvailableCourse(Integer studentId);
}
