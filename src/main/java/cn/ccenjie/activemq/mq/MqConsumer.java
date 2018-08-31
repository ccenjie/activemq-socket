package cn.ccenjie.activemq.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author cenjunjie
 * 2018/8/30
 */
@Component
public class MqConsumer {

    @JmsListener(destination = "yd", containerFactory = "jmsListenerContainerFactory")
    public void reCC(String text) {
        System.out.println(text);
    }


    @JmsListener(destination = "mf", containerFactory = "jmsListenerContainerFactory")
    public void reMF(String text) {

        // 获取订单交易成功的消息

        // 获取对应的商户的WEBSOKECT SESSION,如果存在则发送语音信息
        System.out.println(text);
    }

}
