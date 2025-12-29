package com.cbs.cinebook.socket;

import com.cbs.cinebook.dto.response.ReservationResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
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
public class ReservationSender extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions= Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session)  {
        sessions.add(session);
        log.info("Connected to web socket session {}", session.getId());
    }
    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus closeStatus) {
        sessions.remove(session);
        log.info("Disconnected from web socket session {}", session.getId());
    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        log.info("Received message from web socket session {}", session.getId());
    }
    public void broadcast(ReservationResponseDTO responseDTO) {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try{
                        String jsonString=objectMapper.writeValueAsString(responseDTO);
                        session.sendMessage(new TextMessage(jsonString));
                    }catch (Exception ex){
                        log.error(ex.getMessage());
                    }
                }
            }
        }
    }
}
