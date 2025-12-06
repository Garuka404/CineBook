package com.cbs.cinebook.config;

import com.cbs.cinebook.socket.AvailableSeatSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final AvailableSeatSender availableSeatSender;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
          registry.addHandler(availableSeatSender,"/status").setAllowedOrigins("*");
    }
}
