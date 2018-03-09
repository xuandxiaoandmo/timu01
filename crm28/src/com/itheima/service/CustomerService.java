package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.Customer;
import com.itheima.bean.PageBean;

public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

	void delete(Customer customer);

	Customer findById(Long cust_id);

	void update(Customer customer);

	List<Customer> findAll();

}
