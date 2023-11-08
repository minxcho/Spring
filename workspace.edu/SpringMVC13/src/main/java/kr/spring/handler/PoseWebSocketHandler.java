package kr.spring.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class PoseWebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 받은 메시지를 그대로 다시 클라이언트로 보냅니다. (에코)
        // Unity로 메시지를 전송하는 로직을 여기에 구현합니다.
        System.out.println("Received msg: " + message.getPayload());
        session.sendMessage(message);
    }
}
