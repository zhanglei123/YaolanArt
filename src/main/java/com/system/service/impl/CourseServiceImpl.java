package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.system.mapper.CollegeMapper;
import com.system.mapper.CourseMapper;
import com.system.po.Course;
import com.system.service.CourseService;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CollegeMapper collegeMapper;
	@Override
	public Boolean removeById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getCountCouse() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

   /* @Autowired
    private SelectedcourseMapper selectedcourseMapper;

    
    public Boolean removeById(Integer id) throws Exception {
    	return null;
    }






	@Override
	public int getCountCouse() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public CourseCustom findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<CourseCustom> findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<CourseCustom> findByTeacherID(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
*/
   
}
