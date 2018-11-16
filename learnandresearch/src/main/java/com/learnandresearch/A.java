package com.learnandresearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.learnandresearch")
public class A{
	
    private Test test;
    
    public A()
    {
    	
    }
    
    @Autowired
    public A(Test test)
    {
    	this.test = test;
    }
	
    @Autowired
	public void setTest(Test test)
	{
		this.test = test;
	}
	
	public Test getTest()
	{
		return test;
	}
}
