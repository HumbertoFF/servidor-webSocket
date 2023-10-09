package br.tcc.WebSocket.controller;

import br.tcc.WebSocket.handler.WebSocketHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WebSocketController {
  private WebSocketHandler webSocketHandler;

  public WebSocketController(WebSocketHandler webSocketHandler){
    this.webSocketHandler = webSocketHandler;

  }
  @PostMapping("/enviar-json")
  public void receberJSON(@RequestBody String json) {

    WebSocketHandler.enviarJSONParaExtensao(json);

  }

}
