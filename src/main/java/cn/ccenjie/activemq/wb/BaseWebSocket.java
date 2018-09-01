package cn.ccenjie.activemq.wb;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;

/**
 * @author cenjunjie
 * 2018/8/31
 */
public class BaseWebSocket extends TextWebSocketHandler {

    private static final String FILE_PATH = "D:/132.PNG";

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        ByteBuffer payload = message.getPayload().asReadOnlyBuffer();
        byte[] array = payload.array();
        try {
            FileOutputStream out = new FileOutputStream(FILE_PATH);
            out.write(array);
        }catch (Exception e) {
            e.printStackTrace();
        }
        super.handleBinaryMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }


}
