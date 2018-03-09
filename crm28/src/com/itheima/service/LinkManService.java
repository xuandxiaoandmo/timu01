package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;

public interface LinkManService {

	void save(LinkMan linkMan);

	PageBean<LinkMan> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

	void delete(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	List<LinkMan> findByCid(DetachedCriteria criteria);

}
