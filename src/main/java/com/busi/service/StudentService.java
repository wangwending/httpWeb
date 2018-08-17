/**
 * 
 */
package com.busi.service;

import com.busi.model.Student;

/**
 * ****************** 类说明 ********************* class : StudentService
 * description :
 * 
 * @author : wang.wending
 * @date : Aug 16, 2018-6:56:34 PM
 * @version : 1.0
 * @see : ***********************************************
 */
public interface StudentService {
	/**
	 * 
	 * ******************************************** method name : selectone
	 * description : 根据id查询
	 * 
	 * @param : @param id
	 * @param : @return
	 * @return : Student modified : wang.wending , Aug 16, 2018-6:59:34 PM
	 * @see : *******************************************
	 */
	Student selectone(int id);

	/**
	 * 
	 * ******************************************** method name : updateone
	 * description : 更新学生信息
	 * 
	 * @param : @param student
	 * @param : @return
	 * @return : int modified : wang.wending , Aug 16, 2018-6:59:53 PM
	 * @see : *******************************************
	 */
	int updateone(Student student);

	/**
	 * 
	 * ******************************************** method name : addone
	 * description : 添加学生记录
	 * 
	 * @param : @param student
	 * @param : @return
	 * @return : int modified : wang.wending , Aug 16, 2018-7:00:07 PM
	 * @see : *******************************************
	 */
	int addone(Student student);

	/**
	 * 
	 * ******************************************** method name : delone
	 * description : 删除学生记录
	 * 
	 * @param : @param id
	 * @param : @return
	 * @return : int modified : wang.wending , Aug 16, 2018-7:00:24 PM
	 * @see : *******************************************
	 */
	int delone(int id);
}
