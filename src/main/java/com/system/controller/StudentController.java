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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.system.base.ResponseCode;
import com.system.enums.StudentSourceTypeEnum;
import com.system.po.Student;
import com.system.po.StudentLinkmanInfo;
import com.system.service.StudentLinkmanInfoService;
import com.system.service.StudentService;
import com.system.util.CommonUtil;
import com.system.util.JsonUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	private Logger logger = Logger.getLogger(StudentController.class);
    @Resource(name = "studentServiceImpl")
    private StudentService studentService;
    @Resource(name = "studentLinkmanInfoServiceImpl")
    private StudentLinkmanInfoService studentLinkmanInfoServiceImpl;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(@RequestParam(value = "draw",required = false,defaultValue = "0") Integer draw, 
    		@RequestParam(value = "start",required = false,defaultValue = "0") Integer start, 
	    	@RequestParam(value = "length",required = false, defaultValue = "10") Integer length,
	    	@RequestParam(value = "student_name_s",required = false) String student_name_s,
	    	@RequestParam(value = "startTime",required = false) Date startTime,
	    	@RequestParam(value = "endTime",required = false) Date endTime,
	    	HttpServletRequest request) throws Exception {
    	 //总条数
        int totalCount = studentService.selectCount(null);
        //查询条件
        int page = (start/length)+1;
        Wrapper<Student> wrapper = new EntityWrapper<Student>().orderBy("id", false);
        if (!StringUtils.isEmpty(student_name_s)) {
        	wrapper = wrapper.like("name", "%"+student_name_s+"%");
		}
        if (startTime != null) {
        	wrapper = wrapper.gt("create_time", startTime);
		}
        if (endTime != null) {
        	wrapper = wrapper.lt("create_time", endTime);
        }
        //过滤后条数
        int filteredTotalCount = studentService.selectCount(wrapper);
        
        Page<Student> selectPage = studentService.selectPage(new Page<Student>(page,length), wrapper);
        List<Student> list = selectPage.getRecords();
        return JsonUtil.toDataTableServerMsg(ResponseCode.SUCCESS, draw,totalCount,filteredTotalCount,list);
    }

    /**
     * @description:获取新生来源方式
     * @author: lei.zhang2@100credit.com
     * @time: 2018年8月29日 下午3:56:54
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getStudentSourceTypeList")
    @ResponseBody
    public String getStudentSourceTypeList() throws Exception {
    	Map<String, Integer> resMap = new HashMap<>();
    	StudentSourceTypeEnum[] enums = StudentSourceTypeEnum.values();
    	for (StudentSourceTypeEnum studentSourceTypeEnum : enums) {
    		resMap.put(studentSourceTypeEnum.getName(), studentSourceTypeEnum.getCode());
		}
    	JSONObject jsonObject = JSONObject.fromObject(resMap);
        return JsonUtil.toResponseObj(ResponseCode.SUCCESS,jsonObject.toString());
    }
    
    
    @RequestMapping(value = "getStudentById")
    @ResponseBody
    public String getStudentById(@RequestParam(value = "id",required = true) Integer id) throws Exception {
    	Student student = studentService.selectById(id);
        return JsonUtil.toResponseObj(ResponseCode.SUCCESS,student);
    }
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(@ModelAttribute Student student,@ModelAttribute StudentLinkmanInfo studentLinkmanInfo,
    		HttpServletRequest request) throws Exception {
    	//学生图片为Base64格式
		String uploadDir = request.getServletContext().getRealPath("/upload");// 获取上传目录的路径
		// 获得目录，如果目录不存在，则创建目录
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String newFileName = student.getName()+"_"+sdf.format(new Date());
		String suffix = CommonUtil.getBASE64FileSuffix(student.getImagePath());
		
		String fileNameFull = uploadDir + "\\" + newFileName+suffix;
		//保存文件
		CommonUtil.saveBASE64File(student.getImagePath().split(",")[1], fileNameFull);

        String serverImagePath = "/upload/"+newFileName+suffix;
        student.setImagePath(serverImagePath);
    	
    	student.setCreateTime(new Date());
    	student.setUpdateTime(new Date());
    	student.setStatus(1);
    	student.setRemark(request.getParameter("remark"));
    	studentService.insert(student);
    	//保存联系人信息
    	studentLinkmanInfo.setRemark("");
    	studentLinkmanInfo.setStudentId(student.getId());
    	studentLinkmanInfo.setIsFirst(1);
    	studentLinkmanInfoServiceImpl.insert(studentLinkmanInfo);
    	return JsonUtil.toResponseMsg(ResponseCode.SUCCESS);
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Student student,HttpServletRequest request) throws Exception {
    	//需要判断图片是否更改过
    	Student sea = studentService.selectById(student.getId());
    	if (!student.getImagePath().equals(sea.getImagePath())) {
    		//教师图片为Base64格式
    		String uploadDir = request.getServletContext().getRealPath("/upload");// 获取上传目录的路径
    		// 获得目录，如果目录不存在，则创建目录
    		File dirPath = new File(uploadDir);
    		if (!dirPath.exists()) {
    			dirPath.mkdirs();
    		}

    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String newFileName = student.getName()+"_"+sdf.format(new Date());
    		String suffix = CommonUtil.getBASE64FileSuffix(student.getImagePath());
    		
    		String fileNameFull = uploadDir + "\\" + newFileName+suffix;
    		//保存文件
    		CommonUtil.saveBASE64File(student.getImagePath().split(",")[1], fileNameFull);

            String serverImagePath = "/upload/"+newFileName+suffix;
            student.setImagePath(serverImagePath);
		}
    	
    	student.setUpdateTime(new Date());
    	studentService.insertOrUpdate(student);
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
			logger.error("上传学员图片异常",e);
			return JsonUtil.toResponseMsg(ResponseCode.ERROR);
		}
		return JsonUtil.toResponseMsg(ResponseCode.SUCCESS,serverImagePath);
	}

}
