package com.example.demo.spring;

import org.springframework.stereotype.Component;
 
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
 
/**
 * Created by wzj on 2018/3/14.
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket
{
    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
 
    /**
     * 所有的对象
     */
    public static List<MyWebSocket> webSockets = new CopyOnWriteArrayList<MyWebSocket>();
 
    /**
     * 会话
     */
    private Session session;
 
    /**
     * 建立连接
     *
     * @param session
     */
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
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        System.out.println("[WS] Message from client: " + message);
 
        sendMessage("[WS] Welcome!");
        //sendMessage("the message is: " + message);
        
        Iterator<MyWebSocket> myWebSocketIterator = MyWebSocket.webSockets.iterator();
		while(myWebSocketIterator.hasNext())
		{
			myWebSocketIterator.next().sendMessage("[WS] The message is: " + message);
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

