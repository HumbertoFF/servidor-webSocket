package br.tcc.WebSocket.config;

import org.apache.tomcat.websocket.WsRemoteEndpointAsync;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;
import br.tcc.WebSocket.handler.WebSocketHandler;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(new WebSocketHandler() {
      @Override
      public void afterConnectionEstablished(WebSocketSession session) throws Exception {
         System.out.println("Conectado com a extensão");
        WebSocketHandler.adicionarCliente(session);

      }

      @Override
      public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
         session.sendMessage(message);
      }

      @Override
      public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

      }

      @Override
      public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Conexão encerrada");
        WebSocketHandler.removerCliente(session);
      }

      @Override
      public boolean supportsPartialMessages() {
        return false;
      }
    }, "/ws").setAllowedOriginPatterns("*");
  }
}
