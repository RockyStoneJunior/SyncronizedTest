package com.learnandresearch.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learnandresearch.persistence.dao.UserRepository;
import com.learnandresearch.persistence.model.User;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/user/query", method = RequestMethod.GET)
	@ResponseBody
	public List<User> userQuery(){
		return userRepository.findAll();
	}
}
