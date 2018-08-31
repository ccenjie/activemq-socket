package cn.ccenjie.activemq.mq;

import cn.ccenjie.activemq.utils.ParamUtils;
import cn.ccenjie.activemq.wb.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author cenjunjie
 * 2018/8/30
 */
@Component
public class MqConsumer {

    @Autowired
    private MessageHandler messageHandler;

    @JmsListener(destination = "yd", containerFactory = "jmsListenerContainerFactory")
    public void reCC(String text) {
        System.out.println(text);
    }


    @JmsListener(destination = "mf", containerFactory = "jmsListenerContainerFactory")
    public void reMF(String text) {

        // 获取对应的商户的WEBSOKECT SESSION,如果存在则发送语音信息
        CopyOnWriteArraySet<MessageHandler> onlineConne = messageHandler.getOnlineConne();
        for(MessageHandler mh : onlineConne) {
            // Map<String, String> pathParameters = mh.getSession().getPathParameters(); // 暂时不知道这个API有什么用

            String queryString = mh.getSession().getQueryString();
            if("wb".equals(ParamUtils.getValue(queryString, "busType")) && text.equals(ParamUtils.getValue(queryString, "pid"))) {
                // 找到了对应的业务类型, 那么主动对他发送消息
                mh.getSession().getAsyncRemote().sendText("落霞与孤鹜齐飞");
                return;
            }
        }
    }

}
