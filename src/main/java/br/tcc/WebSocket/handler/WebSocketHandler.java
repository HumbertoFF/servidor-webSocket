package br.tcc.WebSocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {}
  // Lista de conexões WebSocket para clientes
  private static final List<WebSocketSession> clientes = new ArrayList<>();
  // Método para adicionar uma conexão WebSocket à lista de clientes
  public static void adicionarCliente(WebSocketSession session) {
    clientes.add(session);
  }
  // Método para remover uma conexão WebSocket da lista de clientes
  public static void removerCliente(WebSocketSession session) {
    clientes.remove(session);
  }
  // Método para enviar uma mensagem JSON para todas as conexões WebSocket dos clientes
  public static void enviarJSONParaExtensao(String json) {
    // Percorra todas as conexões WebSocket dos clientes e envie a mensagem
    for (WebSocketSession client : clientes) {
      try {
        client.sendMessage(new TextMessage(json));
        //enviar o id para a extensão
        client.sendMessage(new TextMessage(client.getId()));
       } catch (IOException e) {
        // Lide com exceções de envio, se necessário
        e.printStackTrace();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println("JSON recebido no servidor: " + json);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
    // Processar a mensagem recebida
    String mensagemRecebida = message.getPayload();
    System.out.println("Mensagem recebida do cliente: " + mensagemRecebida);

    // Se desejar, você pode responder ao cliente
    String resposta = "Mensagem recebida com sucesso: " + mensagemRecebida;
    session.sendMessage(new TextMessage(resposta));
  }
}

