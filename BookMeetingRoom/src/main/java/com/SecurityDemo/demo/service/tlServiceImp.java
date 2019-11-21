package com.SecurityDemo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SecurityDemo.demo.repository.tlRepository;

@Service
public class tlServiceImp implements tlService {
	
	@Autowired
	tlRepository tlRepository;

	@Override
	public String getTlName(String dept) {
		
		return tlRepository.getTlName(dept);
	}

}
