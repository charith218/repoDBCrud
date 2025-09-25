package com.klu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service

public class Service {
	@Autowired
	Repo r1;
	public String insertData(Product p) {
		r1.save(p);
		return "Tsert success";
	}
	public List<Product> displayData(){
		return r1.findAll();
	}

}
