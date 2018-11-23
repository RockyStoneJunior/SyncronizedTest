package com.example.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.dto.UserLoginForm;
import com.example.demo.persistence.dao.AccountRepository;
import com.example.demo.persistence.dao.BranchRepository;
import com.example.demo.persistence.dao.LoginHistoryRepository;
import com.example.demo.persistence.dao.MerchantRepository;
import com.example.demo.persistence.dao.OnlineUserRepository;
import com.example.demo.persistence.model.Account;
import com.example.demo.persistence.model.Branch;
import com.example.demo.persistence.model.LoginHistory;
import com.example.demo.persistence.model.OnlineUser;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
 
@ServerEndpoint(value = "/new_order")
@Component
public class NewOrderPrompt
{
    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
 
    /**
     * 所有的对象
     */
    public static List<NewOrderPrompt> webSockets = new CopyOnWriteArrayList<NewOrderPrompt>();
    public static HashMap<String, NewOrderPrompt> branchSocket = new HashMap<String, NewOrderPrompt>();
    public static HashMap<Long, List<String>> userList = new HashMap<Long, List<String>>();
    public static HashMap<String, Long> branchList = new HashMap<String, Long>();
    
    private Long branch_id = new Long(0);
    private String username;
    private String branch_name;
    private Date login_date;
 
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
	
	@Autowired
	private OnlineUserRepository onlineUserRepository;
	
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;
    
    public static NewOrderPrompt newOrderPrompt;
    
    @PostConstruct
    public void  init()
    {
    	newOrderPrompt = this;
    	newOrderPrompt.branchRepository = this.branchRepository;
    	newOrderPrompt.accountRepository = this.accountRepository;
    	newOrderPrompt.merchantRepository = this.merchantRepository;
    	newOrderPrompt.onlineUserRepository = this.onlineUserRepository;
    	newOrderPrompt.loginHistoryRepository = this.loginHistoryRepository;
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
    	List<OnlineUser > onlineUser = newOrderPrompt.onlineUserRepository.findByUsername(username);
    	newOrderPrompt.onlineUserRepository.delete(onlineUser);
    	
    	LoginHistory historyUser = new LoginHistory();
    	historyUser.setUsername(username);
    	historyUser.setBranchNname(branch_name);
    	historyUser.setBranchId(branch_id);
    	historyUser.setLoginDate(login_date);
    	historyUser.setLogoutDate(new Date());
    	newOrderPrompt.loginHistoryRepository.save(historyUser);
    	
        onlineNumber--;
        webSockets.remove(this);
        branchSocket.remove(username);
        userList.get(branch_id).remove(username);
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
		String token = forms.get(0).getForm().get(0).getToken();
		Long branch_id = forms.get(0).getForm().get(0).getBranch_id();
		String branchNname = forms.get(0).getForm().get(0).getBranch();
		String message_type = forms.get(0).getType();
		
        System.out.println("token:" + token);
        
        HttpSession httpSession = SessionListener.getSession(token);
        
        if(message_type.equals("login"))
        {
	        if(httpSession != null)
	        {
	        	sendMessage("success");
	        	
	        	this.username = username;
	        	this.branch_id = branch_id;
	        	this.branch_name = branchNname;
	        	this.login_date = new Date();
	        	branchSocket.put(username, this);
	        	
	        	OnlineUser onlineUser = new OnlineUser();
	        	onlineUser.setUsername(this.username);
	        	onlineUser.setBranchNname(this.branch_name);
	        	onlineUser.setBranchId(this.branch_id);
	        	onlineUser.setDate(this.login_date);
	        	newOrderPrompt.onlineUserRepository.save(onlineUser);
	        	
	        	if(userList.containsKey(branch_id))
	        	{
	        		if(userList.get(branch_id).contains(username) == false)
	        		{
	        			userList.get(branch_id).add(username);
	        		}else {
	        			sendMessage("failed");
	        			this.session.close();
	        		}
	        	}else {
	        		List<String> list = new LinkedList<String>();
	        		list.add(username);
	        		userList.put(branch_id, list);
	        	}
	        }else {
	        	
	        	/*
	        	 * When connected by unlogin user, close socket to reclaim resources
	        	 */
	        	sendMessage("failed");
	        	this.session.close();
	        }
        }else if(message_type.equals("heart"))
        {
        	System.out.println("heart from: " + username);
        }
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

