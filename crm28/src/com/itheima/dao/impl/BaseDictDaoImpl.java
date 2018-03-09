package com.itheima.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.BaseDict;
import com.itheima.dao.BaseDictDao;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	@Override
	public List<BaseDict> findByType(String dict_type_code) {
		
		String hql = "from BaseDict where dict_type_code = ?";
		return (List<BaseDict>) getHibernateTemplate().find(hql, dict_type_code);
	}

}
