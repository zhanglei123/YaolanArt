package com.system.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.system.base.ResponseCode;
import com.system.enums.EducationTypeEnum;
import com.system.enums.LessonTypeEnum;
import com.system.enums.TeacherStatusEnum;
import com.system.po.Teacher;
import com.system.service.TeacherService;
import com.system.util.CommonUtil;
import com.system.util.JsonUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
	private Logger logger = Logger.getLogger(TeacherController.class);
	@Resource
    private TeacherService teacherService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request) throws Exception {
        Wrapper<Teacher> wrapper = new EntityWrapper<Teacher>().orderBy("id", false);
        List<Teacher> list = teacherService.selectList(wrapper);
        return JsonUtil.toDataTableFrontMsg(ResponseCode.SUCCESS,list);
    }
    
    
    @RequestMapping(value = "getTeacherById")
    @ResponseBody
    public String getTeacherById(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	Teacher teacher = teacherService.selectById(id);
        return JsonUtil.toResponseObj(ResponseCode.SUCCESS,teacher);
    }
    
	@RequestMapping(value = "/add")
    @ResponseBody
    public String add(@ModelAttribute Teacher teacher,HttpServletRequest request) throws Exception {
    	//教师图片为Base64格式
		String uploadDir = request.getServletContext().getRealPath("/upload");// 获取上传目录的路径
		// 获得目录，如果目录不存在，则创建目录
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String newFileName = teacher.getName()+"_"+sdf.format(new Date());
		String suffix = CommonUtil.getBASE64FileSuffix(teacher.getImagePath());
		
		String fileNameFull = uploadDir + "\\" + newFileName+suffix;
		//保存文件
		CommonUtil.saveBASE64File(teacher.getImagePath().split(",")[1], fileNameFull);

        String serverImagePath = "/upload/"+newFileName+suffix;
        teacher.setImagePath(serverImagePath);
    	teacher.setCreateTime(new Date());
    	teacherService.insert(teacher);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Teacher teacher,HttpServletRequest request) throws Exception {
    	//需要判断图片是否更改过
    	Teacher tea = teacherService.selectById(teacher.getId());
    	if (!teacher.getImagePath().equals(tea.getImagePath())) {
    		//教师图片为Base64格式
    		String uploadDir = request.getServletContext().getRealPath("/upload");// 获取上传目录的路径
    		// 获得目录，如果目录不存在，则创建目录
    		File dirPath = new File(uploadDir);
    		if (!dirPath.exists()) {
    			dirPath.mkdirs();
    		}

    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String newFileName = teacher.getName()+"_"+sdf.format(new Date());
    		String suffix = CommonUtil.getBASE64FileSuffix(teacher.getImagePath());
    		
    		String fileNameFull = uploadDir + "\\" + newFileName+suffix;
    		//保存文件
    		CommonUtil.saveBASE64File(teacher.getImagePath().split(",")[1], fileNameFull);

            String serverImagePath = "/upload/"+newFileName+suffix;
            teacher.setImagePath(serverImagePath);
		}
    	
    	teacher.setUpdateTime(new Date());
    	teacherService.insertOrUpdate(teacher);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    /**
     * @description:获取课程分类信息
     * 				从枚举类中获取，后期如升级可从字典中获取
     * @author: lei.zhang2@100credit.com
     * @time: 2018年8月29日 下午3:56:54
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getCourseTypeList")
    @ResponseBody
    public String getCourseTypeList() throws Exception {
    	Map<String, Integer> resMap = new HashMap<>();
    	LessonTypeEnum[] enums = LessonTypeEnum.values();
    	for (LessonTypeEnum LessonTypeEnum : enums) {
    		resMap.put(LessonTypeEnum.getName(), LessonTypeEnum.getCode());
		}
    	JSONObject jsonObject = JSONObject.fromObject(resMap);
        return JsonUtil.toResponseObj(ResponseCode.SUCCESS,jsonObject.toString());
    }
    
    /**
     * @description:获取学历分类信息
     * 				从枚举类中获取，后期如升级可从字典中获取
     * @author: lei.zhang2@100credit.com
     * @time: 2018年8月29日 下午3:56:54
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getEducationTypeList")
    @ResponseBody
    public String getEducationTypeList() throws Exception {
    	Map<String, Integer> resMap = new HashMap<>();
    	EducationTypeEnum[] enums = EducationTypeEnum.values();
    	for (EducationTypeEnum educationTypeEnum : enums) {
    		resMap.put(educationTypeEnum.getName(), educationTypeEnum.getCode());
		}
    	JSONObject jsonObject = JSONObject.fromObject(resMap);
        return JsonUtil.toResponseObj(ResponseCode.SUCCESS,jsonObject.toString());
    }
    
    /**
     * @description:获取教师状态信息
     * 				从枚举类中获取，后期如升级可从字典中获取
     * @author: lei.zhang2@100credit.com
     * @time: 2018年8月29日 下午3:56:54
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getTeacherStatusList")
    @ResponseBody
    public String getTeacherStatusList() throws Exception {
    	Map<String, Integer> resMap = new HashMap<>();
    	TeacherStatusEnum[] enums = TeacherStatusEnum.values();
    	for (TeacherStatusEnum teacherStatusEnum : enums) {
    		resMap.put(teacherStatusEnum.getName(), teacherStatusEnum.getCode());
		}
    	JSONObject jsonObject = JSONObject.fromObject(resMap);
        return JsonUtil.toResponseObj(ResponseCode.SUCCESS,jsonObject.toString());
    }
    
    
    @RequestMapping(value = "/uploadPersonImage")
    @ResponseBody
	public String uploadPersonImage(HttpServletRequest request, @RequestParam("filedata") MultipartFile file) throws Exception {
		request.setCharacterEncoding("utf-8");// 解决乱码问题
		//返回服务器图片路径
		String serverImagePath = "";
		try {
			String uploadDir = request.getServletContext().getRealPath("/upload");// 获取上传目录的路径
			// 获得目录，如果目录不存在，则创建目录
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			// 开始文件上传
			InputStream inputStream = file.getInputStream(); // 获得输入流
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        String newFileName = sdf.format(new Date());
			String fileName = file.getOriginalFilename(); // 获得原始名字
			String[] strs=fileName.split("\\.");
			String suffix = "."+strs[strs.length-1];
			
			String fileNameFull = uploadDir + "\\" + newFileName+suffix;
			OutputStream outputStream = new FileOutputStream(fileNameFull);// 获得输出流
			int bytesRead = 0;
			byte[] buffer = new byte[8192];

			while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
				// 输出到目标文件夹
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.close();
			// close the stream
			inputStream.close();
			
			serverImagePath = "/upload/"+newFileName+suffix;
		} catch (Exception e) {
			logger.error("上传教师图片异常",e);
			return JsonUtil.toResponseMsg(ResponseCode.ERROR);
		}
		return JsonUtil.toResponseMsg(ResponseCode.SUCCESS,serverImagePath);
	}
    
}
