package com.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.mapper.CollegeMapper;
import com.system.mapper.CourseMapper;
import com.system.mapper.TeacherMapper;
import com.system.po.Teacher;
import com.system.service.TeacherService;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CourseMapper courseMapper;

	@Override
	public void removeById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCountTeacher() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

  
}
