package com.stone.controller;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;  
  
import com.stone.bean.User;  
import com.stone.mapper.UserMapper;  
  
@RestController  
@RequestMapping("/web")  
public class WebController {  
    @Autowired  
    private UserMapper mapper;
      
      
    @RequestMapping("/index")  
    public User selectAge(int age){  
          
        return mapper.Select(age);  
    }  
}  
