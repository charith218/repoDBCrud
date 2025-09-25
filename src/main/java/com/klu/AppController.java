package com.klu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AppController {
	@Autowired
	Service s;
	@PostMapping("/insert")
	
	public String insert(@RequestBody Product p) {
		return s.insertData(p);
	}
	
	@GetMapping("/display")
	public List<Product> display(){
		return s.displayData();
		
	}

}
