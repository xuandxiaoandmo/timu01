package com.itheima.service;

import java.util.List;

import com.itheima.bean.BaseDict;

public interface BaseDictService {

	List<BaseDict> findByType(String dict_type_code);

}
