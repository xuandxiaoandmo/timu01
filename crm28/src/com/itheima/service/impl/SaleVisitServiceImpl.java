package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.Customer;
import com.itheima.bean.PageBean;
import com.itheima.bean.SaleVisit;
import com.itheima.dao.SaleVisitDao;
import com.itheima.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	
	private SaleVisitDao saleVisitDao;
	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		//要记得传递离线对象，因为现在有可能是查询某一种类型的数据 ，所以总数也就不一定是整张表的记录数了。
				int totalSize = saleVisitDao.findCount(criteria); //配置  一处  明着配置 真眼
				List<SaleVisit> list = saleVisitDao.findByPage(criteria , currentPage , pageSize); //配置两处 ， 暗处 假眼 firstResult  maxResult
				
				
				PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
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

}
