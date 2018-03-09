package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.LinkMan;

public interface LinkManDao {

	void save(LinkMan linkMan);

	int findCount(DetachedCriteria criteria);

	List<LinkMan> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

	void delete(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	List<LinkMan> findByCid(DetachedCriteria criteria);

}
