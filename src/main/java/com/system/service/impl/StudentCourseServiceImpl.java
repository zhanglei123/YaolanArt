package com.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.mapper.StudentCourseMapper;
import com.system.po.StudentCourse;
import com.system.service.StudentCourseService;

@Service
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseMapper, StudentCourse> implements StudentCourseService {
	@Autowired
    private StudentCourseMapper studentCourseMapper;
	
	/**
	 * 获取学生可用卡次
	 */
	@Override
	public StudentCourse getAvailableCourse(Integer studentId) {
		//该卡次的状态，如果已经有可用的卡次，该卡次冻结
    	StudentCourse studentCourse = selectOne(new EntityWrapper<StudentCourse>()
    									.eq("student_id", studentId)
    									.eq("type", 1)
    									.eq("status", 1));
    	//没有可用的卡次了
    	if (studentCourse == null) {
    		//查询是否有冻结的卡次
    		StudentCourse frozenCourse = selectOne(new EntityWrapper<StudentCourse>()
					.eq("student_id", studentId)
					.eq("type", 1)
					.eq("status", 2)
					.orderBy("id", true));
    		if (frozenCourse == null) {
    			return null;
			}else {
				//冻结改可用
				frozenCourse.setStatus(1);
				studentCourseMapper.updateById(frozenCourse);
				return frozenCourse;
			}
		}else {
			return studentCourse;
		}
	}

  
}
