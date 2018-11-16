package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.persistence.dao.UserRepository;
import com.example.demo.persistence.model.User;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/helloindex")
	@ResponseBody
	public List<User> index()
	{
		System.out.println("Hello Index");
		
		return userRepository.findAll();
	}
}
