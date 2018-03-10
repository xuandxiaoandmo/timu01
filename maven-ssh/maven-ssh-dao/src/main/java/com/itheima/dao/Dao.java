package com.itheima.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.itheima.domain.User;

/**  
 * ClassName:Dao <br/>  
 * Function:  <br/>  
 * Date:     2018年3月9日 下午8:50:33 <br/>       
 */
@Repository
public class Dao extends HibernateDaoSupport{

	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
	}
	
	
	public  User foundUserById(Integer integer) {
		return getHibernateTemplate().get(User.class, integer);
	}
	
	
	
	
	
	
	
}
  
