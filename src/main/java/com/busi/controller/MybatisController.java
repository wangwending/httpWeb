/**
 * 
 */
package com.busi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.busi.model.Student;
import com.busi.service.StudentService;

/**
 * ******************  类说明  *********************
 * class       :  MybatisController
 * description :
 * @author     :  wang.wending
 * @date       :  Aug 15, 2018-5:35:53 PM
 * @version    :  1.0  
 * @see        :                        
 * ***********************************************
 */
@RestController
public class MybatisController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("selectone")
	public String selectone() {
		Student student = studentService.selectone(1);
		String result = JSON.toJSONString(student);
		
		return result;
	}
	
	@RequestMapping("updateone")
	public String updateone() {
		Student s = new Student();
		s.setId(1);
		s.setName("漂亮");
		studentService.updateone(s);
		
		Student student = studentService.selectone(1);
		String result = JSON.toJSONString(student);
		
		return result;
	}
	
	@RequestMapping("addone")
	public String addone() {
		Student s = new Student();
		s.setId(2);
		s.setName("体育");
		s.setSex("F");
		s.setAge(10);
		studentService.addone(s);
		
		Student student = studentService.selectone(2);
		String result = JSON.toJSONString(student);
		
		return result;
	}
	
	@RequestMapping("delone")
	public String delone() {
		int i = studentService.delone(2);
		
		return ""+i;
	}
}
