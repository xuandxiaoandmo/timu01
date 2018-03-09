package com.itheima.dao;

import java.util.List;

import com.itheima.bean.BaseDict;

public interface BaseDictDao {

	List<BaseDict> findByType(String dict_type_code);

}
