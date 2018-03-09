package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.Customer;
import com.itheima.dao.CustomerDao;

public class CustomerDaoImpl extends BaseDaoImpl<Customer>  implements CustomerDao{


	//这个CustomerDaoImpl，我们是交给了spring托管的。 
	/*public CustomerDaoImpl() {
		super(Customer.class);
	}
*/
}
