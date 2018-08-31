package cn.ccenjie.activemq.action;

import cn.ccenjie.activemq.mq.MqProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cenjunjie
 * 2018/8/30
 */
@RequestMapping("/mq")
@RestController
public class MqPublishAction {

    @Autowired
    private MqProvider mqProvider;

    @RequestMapping("/publish")
    public String publish(String title, String msg) {
        mqProvider.publish(title, msg);
        return "title: " + title + ", msg: " + msg;
    }
}
