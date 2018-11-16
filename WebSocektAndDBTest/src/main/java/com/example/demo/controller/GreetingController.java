package com.example.demo.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.hello.Greeting;
import com.example.demo.hello.HelloMessage;
import com.example.demo.persistence.dao.UserRepository;
import com.example.demo.persistence.model.User;
import com.example.demo.spring.MyWebSocket;

@Controller
public class GreetingController {
	
//	@Autowired
//	private SimpMessagingTemplate websocket;
	
	@Autowired
	private UserRepository userRepository;
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception{
		
		//return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + ", this is my first websocket app!");
		
		List<User> list = userRepository.findAll();

		StringBuilder b = new StringBuilder();
		//list.forEach(b::append);
		
		String str = HtmlUtils.htmlEscape(message.getName()) + ":" +list.get(0).getEmail() + list.get(1).getEmail();
		
		
		Iterator<MyWebSocket> myWebSocketIterator = MyWebSocket.webSockets.iterator();
		while(myWebSocketIterator.hasNext())
		{
			myWebSocketIterator.next().sendMessage(str);
		}
		
		return new Greeting(str);
		
//		return new Greeting(b.toString());
	}
	
	//@Scheduled(cron = "*/3 * * * * *")
	public void websocketSend() throws Exception{
		
		System.out.println("auto sned");
		//websocket.convertAndSend("/topic/greetings", "{\"content\":\"auto send!\"}");
	}
}
