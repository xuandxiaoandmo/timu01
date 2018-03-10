package com.itheima.servlet;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.Dao;
import com.itheima.domain.User;

/**  
 * ClassName:Service <br/>  
 * Function:  <br/>  
 * Date:     2018年3月9日 下午8:53:31 <br/>       
 */
@org.springframework.stereotype.Service
@Transactional
public class UserService {

	@Resource
	private Dao dao;
	
	public  User foundUserById(Integer integer) {
		return dao.foundUserById(integer);
	}
	
}
  
