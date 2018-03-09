package com.itheima.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.PageBean;
import com.itheima.bean.SaleVisit;

public interface SaleVisitService {

	void save(SaleVisit saleVisit);

	PageBean<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

}
