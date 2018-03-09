package com.itheima.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.Customer;

/**
 * 这个接口里面只会定义共性的方法
 * @param <T>
 */
public interface BaseDao<T> {

	
	 void save(T t);
	 void delete(T t);
	 void update(T t);
	 
	 int findCount(DetachedCriteria criteria);
	 
	 List<T> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);
	 
	 List<T> findAll();
	 T findById(Serializable id);
	
	
	
}
