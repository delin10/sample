package nil.ed.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * afterConnectionEstablished
 * handleMessage
 * handleTextMessage
 * handlePongMessage？？
 * handleTransportError
 * afterConnectionClosed
 * supportsPartialMessages
 *
 * 可以实现TextWebSocketHandler
 * 只可以收文本消息，收到其他消息会报错
 */
public class SpringWebSocketHandler extends AbstractWebSocketHandler {

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage("ok"));
    }
}
