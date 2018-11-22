package com.example.demo.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.persistence.dao.BranchRepository;
import com.example.demo.persistence.model.Branch;
import com.example.demo.spring.NewOrderPrompt;

@RestController
public class NewOrderController {
	
	@Autowired
	private BranchRepository branchRepository;

	@RequestMapping(name = "/", method= {RequestMethod.GET, RequestMethod.POST})
	public void newOrder(HttpServletRequest request)
	{
		System.out.println("NewOrder");
		
		String name = request.getParameter("name");
		String branch_name_en = request.getParameter("branch");
		
		Branch branch = branchRepository.findByNameEn(branch_name_en);
		
		System.out.println("Params: name=" + name + "; branch=" + branch_name_en + "; branch_id=" + branch.getId());
		
//		if(branch != null)
//		{
//			NewOrderPrompt newOrderPrompt = NewOrderPrompt.branchSocket.get(branch.getId());
//			
//			if(newOrderPrompt != null)
//			{
//				newOrderPrompt.sendMessage("[WS]NewOrder");
//			}
//		}
	}
}
