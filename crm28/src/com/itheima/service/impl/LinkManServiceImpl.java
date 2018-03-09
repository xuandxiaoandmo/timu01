package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.dao.LinkManDao;
import com.itheima.service.LinkManService;
import com.itheima.web.action.LinkManAction;

@Transactional
public class LinkManServiceImpl implements LinkManService {
	
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		List<LinkMan> list = linkManDao.findByPage(criteria , currentPage , pageSize);
		int totalSize = linkManDao.findCount(criteria);
		
		
		
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage((int) Math.ceil(totalSize * 1.0 / pageSize));
		
		pageBean.setPageSize(pageSize);
		pageBean.setTotalSize(totalSize);
		
		pageBean.setList(list);
		
		
		return pageBean;
	}

	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	public List<LinkMan> findByCid(DetachedCriteria criteria) {
		return linkManDao.findByCid(criteria);
	}

}
