package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.SaleVisit;

public interface SaleVisitDao {

	void save(SaleVisit saleVisit);

	int findCount(DetachedCriteria criteria);

	List<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

}
