package cn.ccenjie.activemq.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author cenjunjie
 * 2018/8/30
 */
@Component
public class MqProvider {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发布话题
     * @param title 标题
     * @param msg 消息
     */
    public void publish(String title, String msg) {
        jmsTemplate.convertAndSend(title, msg);
    }
}
