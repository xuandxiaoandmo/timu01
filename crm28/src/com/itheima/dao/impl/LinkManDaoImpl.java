package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.LinkMan;
import com.itheima.dao.LinkManDao;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	@Override
	public void save(LinkMan linkMan) {
		getHibernateTemplate().save(linkMan);
		
	}

	@Override
	public int findCount(final DetachedCriteria criteria) {
		
		//select *  ---> select count(*)
		criteria.setProjection(Projections.rowCount());
		
		/*List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		
		if(list.size() > 0 ){
			return list.get(0).intValue();
		}
		*/
		
		List<Long> list = (List<Long>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<?>>() {

			@Override
			public List<?> doInHibernate(Session session) throws HibernateException {
				
				Criteria executableCriteria = criteria.getExecutableCriteria(session);
				
				executableCriteria.setFirstResult(0);
				
				executableCriteria.setMaxResults(0);
				
				return executableCriteria.list();
			}
		});
		
		if(list.size() > 0 ){
			return list.get(0).intValue();
		}
		
		return 0;
	}

	@Override
	public List<LinkMan> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		/*
		 * 参数二和参数三 其实就是 limit 的两个   ?  ? 
		 * 
		 * firstResult 就是第一个?  跳过前面的多少条记录
		 * maxResult 就是第二个  ?  每页返回多少条记录
		 * limit  ?  , ? 
		 * 1 --------0 ------5
		 * 2 --------5 ------5
		 * 3 --------10 ------5
		 * n --------(n-1) * 5 ------5
		 * 
		 * select count(*)   --- select * 
		 */
		
		criteria.setProjection(null);
		
		return (List<LinkMan>) getHibernateTemplate().findByCriteria(criteria, (currentPage - 1 ) * pageSize, pageSize);
	}

	@Override
	public void delete(LinkMan linkMan) {
		getHibernateTemplate().delete(linkMan);
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return getHibernateTemplate().get(LinkMan.class, lkm_id);
	}

	@Override
	public void update(LinkMan linkMan) {
		getHibernateTemplate().update(linkMan);
	}

	@Override
	public List<LinkMan> findByCid(DetachedCriteria criteria) {
		return (List<LinkMan>) getHibernateTemplate().findByCriteria(criteria);
	}

}
