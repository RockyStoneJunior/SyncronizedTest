package com.learnandresearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class App extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        ApplicationContext  ctx =  SpringApplication.run(App.class,args);
        
        System.out.println(ctx.containsBeanDefinition("stone"));
        System.out.println(ctx.containsBeanDefinition("MyAuthenticationSucessHandler"));
        System.out.println(ctx.containsBeanDefinition("myAuthenticationSuccessHandler"));
        System.out.println(name);
//        System.out.println("Test Bean: " + ctx.containsBeanDefinition("test"));
//        System.out.println((new App()).getStone());
        //System.out.println(ctx.getBean("stone"));
        
//        Test test = (Test)ctx.getBean("test");
//        test.print();
        
//        A a = new A();
//        a.getTest().print();
    }
    
    @Autowired
    static private String name;
    
    @Autowired
    private String stone;
    
    public String getStone() {
    	//test.print();
    	return stone;
    }
    
    @Bean
    public String stone()
    {
    	return "Hello Stone";
    }
    
    
    
    @Bean
    public Test test()
    {
    	return new Test();
    }
}
