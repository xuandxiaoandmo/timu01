package com.itheima.service.impl;

import java.util.List;

import com.itheima.bean.BaseDict;
import com.itheima.dao.BaseDictDao;
import com.itheima.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService{
	private BaseDictDao baseDictDao;
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByType(String dict_type_code) {
		return baseDictDao.findByType(dict_type_code);
	}

}
