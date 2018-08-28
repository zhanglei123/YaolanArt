package com.system.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.system.po.Teacher;
import com.system.service.TeacherService;
import com.system.util.JsonUtil;

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
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(@ModelAttribute Teacher teacher) throws Exception {
    	teacher.setCreateTime(new Date());
    	teacherService.insert(teacher);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Teacher card) throws Exception {
    	card.setUpdateTime(new Date());
    	teacherService.insertOrUpdate(card);
        return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
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
