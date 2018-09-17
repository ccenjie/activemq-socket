package cn.ccenjie.activemq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @author cenjunjie
 * 2018/8/30
 */
//@Configuration
public class ActiveMqConfig {

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setPubSubDomain(true);
        simpleJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return simpleJmsListenerContainerFactory;
    }
}
