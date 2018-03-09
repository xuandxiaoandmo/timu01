package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.Customer;
import com.itheima.bean.PageBean;
import com.itheima.dao.CustomerDao;
import com.itheima.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	
	private CustomerDao customerDao ;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public void save(Customer customer) {

		customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		//要记得传递离线对象，因为现在有可能是查询某一种类型的数据 ，所以总数也就不一定是整张表的记录数了。
		int totalSize = customerDao.findCount(criteria); //配置  一处  明着配置 真眼
		List<Customer> list = customerDao.findByPage(criteria , currentPage , pageSize); //配置两处 ， 暗处 假眼 firstResult  maxResult
		
		
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrentPage(currentPage);
		
		//int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : (totalSize / pageSize ) +1; //11  5
		
		//向上取整。  1.1 -2  2.9 -3  4.5 -5
		int totalPage =  (int) Math.ceil(totalSize*1.0/pageSize);
		pageBean.setTotalPage(totalPage);
		
		pageBean.setPageSize(pageSize);
		pageBean.setTotalSize(totalSize);
		
		pageBean.setList(list);
		
		
		return pageBean;
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
