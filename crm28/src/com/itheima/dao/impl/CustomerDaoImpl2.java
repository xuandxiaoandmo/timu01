package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.Customer;
import com.itheima.dao.CustomerDao;

public class CustomerDaoImpl2 /*extends HibernateDaoSupport implements CustomerDao */{
/*	
	@Override
	public void save(Customer customer) {
		getHibernateTemplate().save(customer);
	}

	//查询总数
	@Override
	public int findCount(DetachedCriteria criteria) {
		//离线对象自打创建出来就没有经过任何的配置， 那么背后的语句就是 select * from customer;
		
		criteria.setProjection(Projections.rowCount());
		
		//select count(*) from customer
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		if(list.size() > 0 ){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {

		*//**
		 * 参数一： 离线对象
		 * 参数二： firstResult 跳过前面的多少条记录。
		 * 参数三： maxResults 每页返回多少条记录。 5
		 * 
		 * select * from customer limit ? , ? ;
		 * 
		 * 1 ======0======5
		 * 2 ======5======5
		 * 3 ======10======5
		 * 
		 * n ======(n-1) * 5======5
		 * 
		 * 
		 * 
		 *//*
		
		//select count(*) from customer  limit ? , ? ;
		//重置findCount针对离线对象做的配置，以便sql语句回到select* 
		
		criteria.setProjection(null);
		
		//select * from customer  limit ? , ? ;
		
		return (List<Customer>) getHibernateTemplate().findByCriteria(criteria, (currentPage-1 ) * pageSize, pageSize);
	}

	@Override
	public void delete(Customer customer) {
		getHibernateTemplate().delete(customer);
	}

	@Override
	public Customer findById(Long cust_id) {
		return getHibernateTemplate().get(Customer.class, cust_id);
	}

	@Override
	public void update(Customer customer) {
		getHibernateTemplate().update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Customer.class));
	}*/

}
