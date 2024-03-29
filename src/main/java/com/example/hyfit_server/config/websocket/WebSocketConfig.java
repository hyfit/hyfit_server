package com.example.hyfit_server.config.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
        private final StompHandler stompHandler; // jwt 토큰 인증 핸들러

        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
                registry.enableSimpleBroker("/sub");
                registry.setApplicationDestinationPrefixes("/pub");
        }

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
                registry
                        .addEndpoint("/ws")
                        .setAllowedOrigins("*");
        }

        // 토큰 인증
//        @Override
//        public void configureClientInboundChannel(ChannelRegistration registration) {
//                registration.interceptors(stompHandler); // 핸들러 등록
//        }
}