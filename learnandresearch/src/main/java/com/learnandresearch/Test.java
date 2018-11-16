package com.learnandresearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Test {

	public void print()
	{
		System.out.println("This is inside Test");
	}
}
