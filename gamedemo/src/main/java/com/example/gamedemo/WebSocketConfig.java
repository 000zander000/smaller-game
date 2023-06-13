package com.example.gamedemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration //初始化
@EnableWebSocket //开启websocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("加载处理器");
        registry.addHandler(new WebSocketPushHandle(),"/zbj webSocketServer")
                .setAllowedOrigins("*");
    }
}
