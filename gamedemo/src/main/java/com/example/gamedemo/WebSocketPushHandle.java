package com.example.gamedemo;

import com.alibaba.fastjson2.JSON;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebSocketPushHandle extends TextWebSocketHandler {
    private static List<WebSocketSession> Sessions= new ArrayList<>();
    private static int person=60;
    private static int ball1=200;


    //enter
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("用户001进入game");
        Sessions.add(session);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("用户发送了内容，内容是"+message.getPayload());
        if ("1".equals(message.getPayload())) {
            person+=10;
        }
         else {
            ball1+=10;
        }
        Map map=new HashMap();
        map.put("person",person);
        map.put("ball1",ball1);
        TextMessage message1 =new TextMessage(JSON.toJSONString(map));
        for (int i=0;i<Sessions.size();i++) {
            Sessions.get(i).sendMessage(message1);
        }



    }
    //exit
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("用户跑路");
        if (session.isOpen()) {
            session.close();
        }
        person=60;
        ball1=200;
        Sessions.remove(session);

    }

}
