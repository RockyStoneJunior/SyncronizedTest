package com.example.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.dto.UserLoginForm;
import com.example.demo.persistence.dao.AccountRepository;
import com.example.demo.persistence.dao.BranchRepository;
import com.example.demo.persistence.dao.MerchantRepository;
import com.example.demo.persistence.model.Account;
import com.example.demo.persistence.model.Branch;
import com.example.demo.utils.MD5;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import java.io.EOFException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
 
@ServerEndpoint(value = "/new_order")
@Component
public class NewOrderPrompt
{
	
//	@Autowired
//	private MerchantRepository merchantRepository;
//	
//	@Autowired
//	private BranchRepository branchRepository;
//	
//	@Autowired
//	private AccountRepository accountRepository;

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
 
    /**
     * 所有的对象
     */
    public static List<NewOrderPrompt> webSockets = new CopyOnWriteArrayList<NewOrderPrompt>();
 
    /**
     * 会话
     */
    private Session session;
 
    /**
     * 建立连接
     *
     * @param session
     */
    
    @Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private AccountRepository accountRepository;
    
    private static NewOrderPrompt newOrderPrompt;
    
    @PostConstruct
    public void  init()
    {
    	newOrderPrompt = this;
    	newOrderPrompt.branchRepository = this.branchRepository;
    	newOrderPrompt.accountRepository = this.accountRepository;
    	newOrderPrompt.merchantRepository = this.merchantRepository;
    }
	
    @OnOpen
    public void onOpen(Session session)
    {
        onlineNumber++;
        webSockets.add(this);
 
        this.session = session;
 
        System.out.println("[WS] New connection! The current online number: " + onlineNumber);
    }
 
    /**
     * 连接关闭
     */
    @OnClose
    public void onClose()
    {
        onlineNumber--;
        webSockets.remove(this);
        System.out.println("[WS]Connection closed! The current online number: " + onlineNumber);
    }
 
    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws 
    JsonParseException, JsonMappingException, EOFException, IOException, NoSuchAlgorithmException
    {
        System.out.println("[WS] Message from client: " + message);
 
//        sendMessage("[WS] Welcome!");
//        sendMessage("the message is: " + message);
        
        ObjectMapper mapper = new ObjectMapper();
        
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        List<UserLoginForm> forms =  mapper.readValue(message, new TypeReference<List<UserLoginForm>>() {});
        
        for(UserLoginForm form : forms)
        {
        	System.out.println(form.toString());
        }
        
        String username = forms.get(0).getForm().get(0).getUsername();
		String password = forms.get(0).getForm().get(0).getPassword();
		
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes("UTF-8"));
        String password_encypted = MD5.byteArrayToHexString(md.digest());
        
        System.out.println(password);
        System.out.println(password_encypted);
        
        if(newOrderPrompt.accountRepository == null)
        {
        	System.out.println("accountRepository is null");
        }
		
		Account account = newOrderPrompt.accountRepository.findByUsernameAndPassword(username, password_encypted);
		
		if(account != null)
		{
			Branch branch = newOrderPrompt.branchRepository.findById(account.getBranch_id());
			
			System.out.println(session.getId());

			sendMessage("success:" + branch.getName());
		}else {
			sendMessage("failed");
		}
        
//        Iterator<NewOrderPrompt> myWebSocketIterator = NewOrderPrompt.webSockets.iterator();
//		while(myWebSocketIterator.hasNext())
//		{
//			myWebSocketIterator.next().sendMessage("[WS] The message is: " + message);
//		}
    }
 
    /**
     * 发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message)
    {
        try
        {
            session.getBasicRemote().sendText(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

