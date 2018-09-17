package cn.ccenjie.activemq.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author cenjunjie
 * 2018/9/17
 */
@MapperScan("cn.ccenjie.activemq.mapper")
@Configuration
public class MybatisConfig {
}
