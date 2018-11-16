package com.learnandresearch.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
public class MvcConfig {//extends WebMvcConfigurerAdapter{
//	public MvcConfig() {
//		super();
//	}
//	
//	@Override
//	public void addViewControllers(final ViewControllerRegistry registry) {
//		super.addViewControllers(registry);
//		registry.addViewController("/").setViewName("forward:index");
//		registry.addViewController("/login");
//		registry.addViewController("/home");
//		registry.addViewController("/index");
//	}
//	
//	@Override
//	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/","/resources/");
//	}
}
