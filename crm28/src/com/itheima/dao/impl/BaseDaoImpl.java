package com.itheima.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.Customer;
import com.itheima.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	/*1. T.class
	2. Object.class
	3. this.getClass
	4. null
	5. Customer.class
	*/
	//这个参数的意思就是表示了告诉hibernate到底要查询哪一个表 。 customer表  --- Customer.class   linkman  -- linkMan.class
	/*	this. 谁来调用这个方法，就是哪个对象。
		new BaseDaoImpl().findAll();
		this.getClass()  ---- BaseDaoImpl.class
		
		这里的参数到底写什么， 其实从父类（BaseDaoImpl）角度来说，它是不知道。
		
		如果是CustomerDaoImpl来继承了BaseDaoImpl，那么这个参数应该写Customer.class
		如果是LinkManDaoImpl来继承了BaseDaoImpl，那么这个参数应该写LinkMan.class
		
		只有子类才知道要写什么。。 所以要求所有的子类来和父类继承的时候，一定要带具体的class过来。
		
		父类如何才能问子类要东西呢? ---> 子类给父类传东西
		
	*/
	
	private Class clazz ;
	/*
	public BaseDaoImpl(Class clazz) {
		super();
		this.clazz = clazz;
	}*/
	
	public BaseDaoImpl(){
		//使用反射的手段解析类上面的泛型T的类型。 
		/*
		 * 这个方法返回值是type类型。 
		 * this : 子类对象
		 * this.getClass()  子类字节码
		 * this.getClass().getGenericSuperclass() ： 拿到父类的字节码（这份字节码含有泛型）
		 */
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) pt.getActualTypeArguments()[0];
		System.out.println("class ===" +clazz);
	}

	@Override
	public List<T> findAll() {
		
		return (List<T>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(clazz));
	}

	@Override
	public T findById(Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}
	
	
	//=============================================
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
		
	}

	@Override
	public int findCount(DetachedCriteria criteria) {
		
		criteria.setProjection(Projections.rowCount());
		
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(criteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<T> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
		
		criteria.setProjection(null);
		
		System.out.println("调用BaseDaoImpl的findByPage方法~！~~");
		
		return (List<T>) getHibernateTemplate().findByCriteria(criteria, (currentPage - 1 ) * pageSize, pageSize);
	}



}
