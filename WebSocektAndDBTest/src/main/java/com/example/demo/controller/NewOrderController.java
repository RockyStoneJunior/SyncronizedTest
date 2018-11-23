package com.example.demo.controller;

import java.util.Iterator;
import java.util.List;

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
		String name = request.getParameter("name");
		String branch_name_en = request.getParameter("branch");
		
		Long branch_id = new Long(0);
		
		if(NewOrderPrompt.branchList.containsKey(branch_name_en))
		{
			branch_id = NewOrderPrompt.branchList.get(branch_name_en);
		}else{
			Branch branch = branchRepository.findByNameEn(branch_name_en);
			
			if(branch != null)
			{
				branch_id = branch.getId();
				NewOrderPrompt.branchList.put(branch_name_en, branch_id);
				
				System.out.println("Params: name=" + name + "; branch=" + branch_name_en + "; branch_id=" + branch.getId());
			}else {
				System.out.println(branch_name_en + " doesn't exist!");
				return;
			}
		}
		
		List<String> userSockets = NewOrderPrompt.userList.get(branch_id);
		
		if(userSockets != null)
		{
			for(String username : userSockets)
			{
				NewOrderPrompt newOrderPrompt = NewOrderPrompt.branchSocket.get(username);
				
				if(newOrderPrompt != null)
				{
					System.out.println(username + ":NewOrder");
					newOrderPrompt.sendMessage("[WS]NewOrder");
				}
			}
		}
	}
}
