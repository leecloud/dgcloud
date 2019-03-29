package com.lee.cloudadmin.config;

import com.lee.cloudadmin.dao.TokenPrinciple;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/endpoint").addInterceptors(new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
                ServletServerHttpRequest req = (ServletServerHttpRequest) serverHttpRequest;
                //获取token验证
                String token = req.getServletRequest().getParameter("passcode");
                //解析token获取用户信息
                Principal user = parseToken("aaa");
                if (user == null){
                    return false;//如果token认证失败，拒绝握手返回false
                }
                //认证成功 保存用户
                map.put("user",user);
                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

            }
        }).setHandshakeHandler(new DefaultHandshakeHandler(){
            @Override
            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler webSocketHandler,Map<String, Object> map){
                return (Principal) map.get("user");
            }
        })
                .setAllowedOrigins("*")
                .withSockJS();
    }

    private Principal parseToken(String token) {
        return new TokenPrinciple(token);
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/queue","/topic");
    }
}
