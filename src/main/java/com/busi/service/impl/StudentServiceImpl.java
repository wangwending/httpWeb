/**
 * 
 */
package com.busi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busi.mapper.StudentMapper;
import com.busi.model.Student;
import com.busi.service.StudentService;

/**
 * ******************  类说明  *********************
 * class       :  StudentServiceImpl
 * description :  学生业务实现
 * @author     :  wang.wending
 * @date       :  Aug 16, 2018-6:57:01 PM
 * @version    :  1.0  
 * @see        :                        
 * ***********************************************
 */
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentMapper studentMapper;

	@Override
	public Student selectone(int id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateone(Student student) {
		return studentMapper.updateByPrimaryKeySelective(student);
	}

	@Override
	public int addone(Student student) {
		return studentMapper.insert(student);
	}

	@Override
	public int delone(int id) {
		return studentMapper.deleteByPrimaryKey(id);
	}

}
