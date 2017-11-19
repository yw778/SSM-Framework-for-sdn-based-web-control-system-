package com.main.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.main.dao.LOltMapper;
import com.main.model.LOlt;
@Service
public class LOltService {
	@Resource
	LOltMapper lOltMapper;
	
	 int insertSelective(LOlt record){
		 return lOltMapper.insertSelective(record);
	 }
}
