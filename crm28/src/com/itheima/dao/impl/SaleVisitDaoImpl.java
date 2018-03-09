package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.Customer;
import com.itheima.bean.SaleVisit;
import com.itheima.dao.SaleVisitDao;

public class SaleVisitDaoImpl extends HibernateDaoSupport implements SaleVisitDao{

	@Override
	public void save(SaleVisit saleVisit) {
		getHibernateTemplate().save(saleVisit);
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
		public List<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
			//select count(*) from customer  limit ? , ? ;
			//重置findCount针对离线对象做的配置，以便sql语句回到select* 
			
			criteria.setProjection(null);
			
			//select * from customer  limit ? , ? ;
			
			return (List<SaleVisit>) getHibernateTemplate().findByCriteria(criteria, (currentPage-1 ) * pageSize, pageSize);
		}

}
