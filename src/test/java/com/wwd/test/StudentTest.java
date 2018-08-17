/**
 * 
 */
package com.wwd.test;

import com.alibaba.fastjson.JSON;
import com.busi.model.Student;
import com.busi.service.StudentService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ******************  类说明  *********************
 * class       :  StudentTest
 * description :
 * @author     :  wang.wending
 * @date       :  Aug 16, 2018-7:16:14 PM
 * @version    :  1.0  
 * @see        :                        
 * ***********************************************
 */
public class StudentTest extends BaseTest{
	
	Logger log = LogManager.getLogger(StudentTest.class);
	
	@Autowired
	private StudentService studentService;
	
	@Test
	public void selectoneTest() {
		Student student = studentService.selectone(1);
		String string = JSON.toJSONString(student);
		log.info("Student info: {}", string);
	}
	
	@Test
	public void updateoneTest() {
		Student student = new Student();
		student.setId(1);
		student.setName("和平");
		int i = studentService.updateone(student);
		log.info("update count {}", i);
		
		student = studentService.selectone(1);
		String string = JSON.toJSONString(student);
		log.info("Student info: {}", string);
		
	}
	
	@Test
	public void addoneTest() {
		Student student = new Student();
		student.setId(3);
		student.setName("密码");
		student.setAge(18);
		student.setSex("M");
		int i = studentService.addone(student);
		log.info("add count {}", i);
		
		student = studentService.selectone(3);
		String string = JSON.toJSONString(student);
		log.info("Student info: {}", string);
		
	}
	
	@Test
	public void deloneTest() {
		int i = studentService.delone(3);
		log.info("delete count {}", i);
	}
}
