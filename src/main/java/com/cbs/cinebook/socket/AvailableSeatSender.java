package com.cbs.cinebook.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class AvailableSeatSender extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions= Collections.synchronizedSet(new HashSet<>());


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("Connected to web socket session {}", session.getId());
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessions.remove(session);
        log.info("Disconnected from web socket session {}", session.getId());
    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("Received message from web socket session {}", session.getId());
    }
    public void brodecast(WebSocketSession status) throws Exception {

        synchronized (sessions) {
            for (WebSocketSession session1 : sessions) {
                if (session1 == status) {

                }
            }
        }
    }
}
